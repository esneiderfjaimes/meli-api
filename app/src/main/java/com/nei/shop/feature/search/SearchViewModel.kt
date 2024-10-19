package com.nei.shop.feature.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nei.shop.domain.NetworkService
import com.nei.shop.domain.Product
import com.nei.shop.network.NetworkServiceImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewModel : ViewModel() {

    private val _state: MutableStateFlow<State> = MutableStateFlow(State.Init)
    val state: StateFlow<State> get() = _state

    private val _history: MutableStateFlow<Set<String>> = MutableStateFlow(setOf())
    val history: StateFlow<Set<String>> get() = _history

    private val networkService: NetworkService = NetworkServiceImpl

    fun search(query: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                if (query.isBlank()) {
                    _state.value = State.Init
                    return@withContext
                }

                _state.value = State.Loading

                networkService.search(query).fold(
                    onSuccess = { products ->
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
        if (query.isBlank()) return
        _history.value = _history.value.plus(query)
    }

    sealed class State {
        data object Init : State()
        data object Loading : State()
        data class Error(val message: String) : State()
        data class Success(val products: List<Product>) : State()
    }
}