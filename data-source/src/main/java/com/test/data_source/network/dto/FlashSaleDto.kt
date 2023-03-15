package com.test.data_source.network.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.test.data_source.network.dto.FlashSaleGoodDto

@JsonClass(generateAdapter = true)
class FlashSaleDto(@Json(name = "flash_sale") val flashSale: List<FlashSaleGoodDto>)