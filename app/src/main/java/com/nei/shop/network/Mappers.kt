package com.nei.shop.network

import com.nei.shop.domain.Product

fun List<ResultResponse>.toDomain() = map { it.toDomain() }

fun ResultResponse.toDomain() = Product(
    title = title,
    thumbnail = thumbnail
)