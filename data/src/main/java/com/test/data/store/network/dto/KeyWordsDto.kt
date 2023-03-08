package com.test.data.store.network.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class KeyWordsDto(
    @Json(name = "words") val words: List<String>
)