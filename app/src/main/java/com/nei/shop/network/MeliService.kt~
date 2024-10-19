package com.nei.shop.network

import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET("search")
    suspend fun getPageCharacters(
        @Query("q") query: String,
    ): SearchResponse
}