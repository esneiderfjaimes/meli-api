package com.nei.shop.domain

data class Product(
    val id: String,
    val title: String,
    val thumbnail: String,
    val price: Double,
    val originalPrice: Double,
)
