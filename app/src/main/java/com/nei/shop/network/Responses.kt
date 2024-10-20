package com.nei.shop.network

import com.google.gson.annotations.SerializedName
import java.text.NumberFormat
import java.util.Locale

data class SearchResponse(
    @SerializedName("site_id")
    val siteId: String,
    @SerializedName("country_default_time_zone")
    val countryDefaultTimeZone: String,
    val query: String,
    val paging: Paging,
    val results: List<ResultResponse>,
)

data class Paging(
    val total: Long,
    @SerializedName("primary_results")
    val primaryResults: Long,
    val offset: Long,
    val limit: Long,
)

data class ResultResponse(
    val id: String,
    val title: String,
    val permalink: String,
    val thumbnail: String,
    val price: Double,
    @SerializedName("original_price")
    val originalPrice: Double
)

fun formatToCOP(amount: Double): String {
    val format = NumberFormat.getCurrencyInstance(Locale("es", "CO"))
    format.maximumFractionDigits = 0
    return format.format(amount)
}