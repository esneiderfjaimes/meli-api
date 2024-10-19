package com.nei.shop.feature.detail

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.google.gson.Gson
import com.nei.shop.domain.Product
import com.nei.shop.feature.detail.model.ProductDetail
import com.nei.shop.feature.detail.model.toDetail
import kotlinx.serialization.Serializable

@Serializable
data class DetailRoute(
    val data: String
)

fun NavGraphBuilder.detailScreen(
    onBackClick: () -> Unit
) {
    composable<DetailRoute> {
        val data = it.toRoute<DetailRoute>().data
        val product = Gson().fromJson(data, ProductDetail::class.java)
        DetailScreen(product, onBackClick = onBackClick)
    }
}

fun NavController.navigateToDetailScreen(product: Product) = navigate(
    route = kotlin.run {
        val data = Gson().toJson(product.toDetail())
        DetailRoute(data = data)
    }
) {
    launchSingleTop = true
}