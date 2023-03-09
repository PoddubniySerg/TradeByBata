package com.test.domain.repository

import com.test.domain.entities.Brand
import com.test.domain.entities.FlashSaleGood
import com.test.domain.entities.GoodDetails
import com.test.domain.entities.LatestGood

interface GoodsRepository {

    suspend fun getLatestGoods(): List<LatestGood>

    suspend fun getFlshSaleGoods(): List<FlashSaleGood>

    suspend fun getBrands(): List<Brand>

    suspend fun getGoodDetails(): GoodDetails

    suspend fun getKeyWords(): List<String>
}