package com.test.data_source.network.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.test.core.entities.GoodDetails

@JsonClass(generateAdapter = true)
class GoodDetailsDto(
    @Json(name = "name") override val name: String,
    @Json(name = "description") override val description: String,
    @Json(name = "rating") override val rating: Double,
    @Json(name = "number_of_reviews") override val reviewsCount: Int,
    @Json(name = "price") override val price: Double,
    @Json(name = "colors") override val colors: List<String>,
    @Json(name = "image_urls") override val imageUrls: List<String>
) : GoodDetails