package com.test.data_source.network.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class KeyWordsDto(
    @Json(name = "words") val words: List<String>
)