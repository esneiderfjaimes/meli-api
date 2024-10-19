package com.nei.shop.network

import com.google.gson.annotations.SerializedName

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
/*
    val condition: String,
    @SerializedName("thumbnail_id")
    val thumbnailId: String,
    @SerializedName("catalog_product_id")
    val catalogProductId: String?,
    @SerializedName("listing_type_id")
    val listingTypeId: String,
    @SerializedName("sanitized_title")
    val sanitizedTitle: String,
    @SerializedName("buying_mode")
    val buyingMode: String,
    @SerializedName("site_id")
    val siteId: String,
    @SerializedName("category_id")
    val categoryId: String,
    @SerializedName("domain_id")
    val domainId: String,
    @SerializedName("currency_id")
    val currencyId: String,
    @SerializedName("order_backend")
    val orderBackend: Long,
    val price: Double,
    @SerializedName("original_price")
    val originalPrice: Double,
    @SerializedName("sale_price")
    val salePrice: SalePrice,
    @SerializedName("available_quantity")
    val availableQuantity: Long,
    @SerializedName("official_store_id")
    val officialStoreId: Long?,
    @SerializedName("official_store_name")
    val officialStoreName: String?,
    @SerializedName("use_thumbnail_id")
    val useThumbnailId: Boolean,
    @SerializedName("accepts_mercadopago")
    val acceptsMercadopago: Boolean,
    val shipping: Shipping,
    @SerializedName("stop_time")
    val stopTime: String,
    val seller: Seller,
    val attributes: List<Attribute>,
    val installments: Installments,
    @SerializedName("winner_item_id")
    val winnerItemId: Any?,
    @SerializedName("catalog_listing")
    val catalogListing: Boolean,
    val discounts: Any?,
    val promotions: List<Any?>,
    @SerializedName("inventory_id")
    val inventoryId: Any?,
    @SerializedName("variation_id")
    val variationId: String?,
    @SerializedName("variation_filters")
    val variationFilters: List<String>?,
    @SerializedName("variations_data")
    val variationsData: VariationsData?,*/
)

data class SalePrice(
    @SerializedName("price_id")
    val priceId: String,
    val amount: Double,
    val conditions: Conditions,
    @SerializedName("currency_id")
    val currencyId: String,
    @SerializedName("exchange_rate")
    val exchangeRate: Any?,
    @SerializedName("payment_method_prices")
    val paymentMethodPrices: List<Any?>,
    @SerializedName("payment_method_type")
    val paymentMethodType: String,
    @SerializedName("regular_amount")
    val regularAmount: Long?,
    val type: String,
    val metadata: Metadata,
)

data class Conditions(
    val eligible: Boolean,
    @SerializedName("context_restrictions")
    val contextRestrictions: List<String>,
    @SerializedName("start_time")
    val startTime: String?,
    @SerializedName("end_time")
    val endTime: String?,
)

data class Metadata(
    @SerializedName("campaign_id")
    val campaignId: String?,
    @SerializedName("promotion_id")
    val promotionId: String?,
    @SerializedName("promotion_type")
    val promotionType: String?,
)

data class Shipping(
    @SerializedName("store_pick_up")
    val storePickUp: Boolean,
    @SerializedName("free_shipping")
    val freeShipping: Boolean,
    @SerializedName("logistic_type")
    val logisticType: String,
    val mode: String,
    val tags: List<String>,
    val benefits: Any?,
    val promise: Any?,
    @SerializedName("shipping_score")
    val shippingScore: Long,
)

data class Seller(
    val id: Long,
    val nickname: String,
)

data class Attribute(
    val id: String,
    val name: String,
    @SerializedName("value_id")
    val valueId: String?,
    @SerializedName("value_name")
    val valueName: String?,
    @SerializedName("attribute_group_id")
    val attributeGroupId: String,
    @SerializedName("attribute_group_name")
    val attributeGroupName: String,
    @SerializedName("value_struct")
    val valueStruct: ValueStruct?,
    val values: List<Value>,
    val source: Long,
    @SerializedName("value_type")
    val valueType: String,
)

data class ValueStruct(
    val number: Long,
    val unit: String,
)

data class Value(
    val id: String?,
    val name: String?,
    val struct: Struct?,
    val source: Long,
)

data class Struct(
    val number: Long,
    val unit: String,
)

data class Installments(
    val quantity: Long,
    val amount: Double,
    val rate: Double,
    @SerializedName("currency_id")
    val currencyId: String,
    val metadata: Metadata2,
)

data class Metadata2(
    @SerializedName("meliplus_installments")
    val meliplusInstallments: Boolean,
    @SerializedName("additional_bank_interest")
    val additionalBankInterest: Boolean,
)

data class VariationsData(
    @SerializedName("181930821924")
    val n181930821924: n181930821924?,
    @SerializedName("185490649493")
    val n185490649493: n185490649493?,
    @SerializedName("179643519140")
    val n179643519140: n179643519140?,
)

data class n181930821924(
    val thumbnail: String,
    val ratio: String,
    val name: String,
    @SerializedName("pictures_qty")
    val picturesQty: Long,
    val price: Long,
    @SerializedName("user_product_id")
    val userProductId: String,
    val attributes: List<Any?>,
    @SerializedName("attribute_combinations")
    val attributeCombinations: Any?,
)

data class n185490649493(
    val thumbnail: String,
    val ratio: String,
    val name: String,
    @SerializedName("pictures_qty")
    val picturesQty: Long,
    val price: Long,
    @SerializedName("user_product_id")
    val userProductId: String,
    val attributes: List<Any?>,
    @SerializedName("attribute_combinations")
    val attributeCombinations: Any?,
)

data class n179643519140(
    val thumbnail: String,
    val ratio: String,
    val name: String,
    @SerializedName("pictures_qty")
    val picturesQty: Long,
    val price: Long,
    @SerializedName("user_product_id")
    val userProductId: String,
    val attributes: List<Attribute2>,
    @SerializedName("attribute_combinations")
    val attributeCombinations: Any?,
)

data class Attribute2(
    val id: String,
    val name: String,
    @SerializedName("value_name")
    val valueName: String,
    @SerializedName("value_type")
    val valueType: String,
)
