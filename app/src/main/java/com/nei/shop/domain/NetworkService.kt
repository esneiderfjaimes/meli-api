package com.nei.shop.domain

interface NetworkService {
    suspend fun search(query: String): Result<List<Product>>
}