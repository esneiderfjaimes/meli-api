package com.nei.shop.feature.search

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.nei.shop.domain.Product
import kotlinx.serialization.Serializable

@Serializable
data object SearchRoute

fun NavGraphBuilder.searchScreen(
    onProductClick: (Product) -> Unit,
) {
    composable<SearchRoute> {
        SearchRoute(
            animatedContentScope = this,
            onProductClick = onProductClick
        )
    }
}

@Composable
fun SearchRoute(
    animatedContentScope: AnimatedContentScope,
    viewModel: SearchViewModel = viewModel(),
    onProductClick: (Product) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val history by viewModel.history.collectAsStateWithLifecycle()
    SearchScreen(
        state = state,
        animatedVisibilityScope = animatedContentScope,
        onSearch = viewModel::search,
        history = history,
        onHistory = viewModel::addHistory,
        onProductClick = onProductClick
    )
}