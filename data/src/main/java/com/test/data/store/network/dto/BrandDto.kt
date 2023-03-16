package com.test.data.store.network.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.test.core.entities.Brand

@JsonClass(generateAdapter = true)
class BrandDto( @Json(name = "image_url") override val posterUrl: String) : Brand