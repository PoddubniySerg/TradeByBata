package com.test.data.store.network.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.test.core.entities.FlashSaleGood

@JsonClass(generateAdapter = true)
class FlashSaleGoodDto(
    @Json(name = "discount") override val discount: Int,
    @Json(name = "category") override val category: String,
    @Json(name = "name") override val name: String,
    @Json(name = "price") override val price: Double,
    @Json(name = "image_url") override val imageUrl: String
) : FlashSaleGood