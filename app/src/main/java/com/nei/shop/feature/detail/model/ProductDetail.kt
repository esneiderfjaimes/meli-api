package com.nei.shop.feature.detail.model

import kotlinx.serialization.Serializable

@Serializable
data class ProductDetail(
    val id: String,
    val title: String,
    val thumbnail: String,
    val price: Double,
    val originalPrice: Double,
)
