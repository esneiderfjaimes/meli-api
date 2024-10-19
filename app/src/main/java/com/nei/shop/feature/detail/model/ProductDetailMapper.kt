package com.nei.shop.feature.detail.model

import com.nei.shop.domain.Product

fun Product.toDetail() = ProductDetail(id, title, thumbnail, price, originalPrice)