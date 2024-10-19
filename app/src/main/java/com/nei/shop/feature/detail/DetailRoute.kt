package com.nei.shop.feature.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data class DetailRoute(
    val id: String
)

fun NavGraphBuilder.detailScreen() {
    composable<DetailRoute> {
        DetailRoute()
    }
}

fun NavController.navigateToDetailScreen(id: String) = navigate(DetailRoute(id)) {
    launchSingleTop = true
}

@Composable
fun DetailRoute(
    viewModel: DetailViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    DetailScreen(
        state = state,
    )
}