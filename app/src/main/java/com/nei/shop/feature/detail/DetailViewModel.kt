package com.nei.shop.feature.detail

import androidx.lifecycle.ViewModel
import com.nei.shop.domain.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DetailViewModel : ViewModel() {
    private val _state: MutableStateFlow<DetailState> = MutableStateFlow(DetailState.Init)
    val state: StateFlow<DetailState> get() = _state

    sealed class DetailState {
        data object Init : DetailState()
        data object Loading : DetailState()
        data class Error(val message: String) : DetailState()
        data class Success(val products: List<Product>) : DetailState()
    }
}