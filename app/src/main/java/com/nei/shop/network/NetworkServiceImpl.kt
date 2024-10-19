package com.nei.shop.network

import com.nei.shop.domain.NetworkService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkServiceImpl : NetworkService {
    private const val URL = "https://api.mercadolibre.com/sites/MCO/"

    private val okHttpClient: OkHttpClient = HttpLoggingInterceptor().run {
        level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder().addInterceptor(this).build()
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val meliService = retrofit.create(MeliService::class.java)

    override suspend fun search(query: String) = runCatching {
        meliService.search(query).results.toDomain()
    }
}