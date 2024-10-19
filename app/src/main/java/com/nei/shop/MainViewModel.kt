package com.nei.shop

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nei.shop.domain.Product
import com.nei.shop.network.SearchService
import com.nei.shop.network.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel : ViewModel() {
    private val _state: MutableStateFlow<State> = MutableStateFlow(State.Init)
    val state: StateFlow<State> get() = _state
    private val _history: MutableStateFlow<Set<String>> = MutableStateFlow(setOf())
    val history: StateFlow<Set<String>> get() = _history

    val okHttpClient: OkHttpClient = HttpLoggingInterceptor().run {
        level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder().addInterceptor(this).build()
    }
    val build = Retrofit.Builder()
        .baseUrl("https://api.mercadolibre.com/sites/MCO/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun search(query: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                if (query.isBlank()) {
                    _state.value = State.Init
                    return@withContext
                }

                _state.value = State.Loading

                val pageCharacters =
                    kotlin.runCatching {
                        build.create(SearchService::class.java).getPageCharacters(query)
                    }
                pageCharacters.fold(
                    onSuccess = {
                        val products = it.results.toDomain()
                        Log.d("MainViewModel", "search: products:$products")
                        _state.value = State.Success(products)
                    },
                    onFailure = {
                        _state.value = State.Error(it.message.orEmpty())
                    }
                )
            }
        }
    }

    fun addHistory(query: String) {
        _history.value = _history.value.plus(query)
    }

    sealed class State {
        data object Init : State()
        data object Loading : State()
        data class Error(val message: String) : State()
        data class Success(val products: List<Product>) : State()
    }
}