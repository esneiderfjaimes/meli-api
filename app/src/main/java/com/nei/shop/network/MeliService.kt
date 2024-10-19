package com.nei.shop.network

import retrofit2.http.GET
import retrofit2.http.Query

interface MeliService {

    @GET("search")
    suspend fun search(
        @Query("q") query: String,
    ): SearchResponse
}