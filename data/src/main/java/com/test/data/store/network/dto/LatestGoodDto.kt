package com.test.data.store.network.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.test.domain.entities.FlashSaleGood
import com.test.domain.entities.LatestGood

@JsonClass(generateAdapter = true)
class LatestGoodDto(
    @Json(name = "category") override val category: String,
    @Json(name = "name") override val name: String,
    @Json(name = "price") override val price: Double,
    @Json(name = "image_url") override val imageUrl: String
) : LatestGood, com.test.core.entities.LatestGood