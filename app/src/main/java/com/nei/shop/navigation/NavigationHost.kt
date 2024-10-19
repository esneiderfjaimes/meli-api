package com.nei.shop.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.nei.shop.feature.detail.detailScreen
import com.nei.shop.feature.detail.navigateToDetailScreen
import com.nei.shop.feature.search.SearchRoute
import com.nei.shop.feature.search.searchScreen

@ExperimentalSharedTransitionApi
@Composable
fun NavigationHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = SearchRoute,
    ) {
        searchScreen(
            onProductClick = { navController.navigateToDetailScreen(it) }
        )
        detailScreen(
            onBackClick = { navController.popBackStack() }
        )
    }
}