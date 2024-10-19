package com.nei.shop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nei.shop.feature.home.Home
import com.nei.shop.ui.theme.ShopTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    @ExperimentalLayoutApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val state by viewModel.state.collectAsStateWithLifecycle()

            val history by viewModel.history.collectAsStateWithLifecycle()
            ShopTheme {
                Home(
                    state = state,
                    onSearch = viewModel::search,
                    history = history,
                    onHistory = viewModel::addHistory,
                )
            }
        }
    }
}