package com.test.data.store.network.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class FlashSaleDto(@Json(name = "flash_sale") val flashSale: List<FlashSaleGoodDto>)