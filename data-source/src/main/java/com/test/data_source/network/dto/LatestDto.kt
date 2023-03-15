package com.test.data_source.network.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class LatestDto(@Json(name = "latest") val latest: List<LatestGoodDto>)