package com.test.feature_page1.domain.repositories

import com.test.core.entities.Brand
import com.test.core.entities.FlashSaleGood
import com.test.core.entities.GoodDetails
import com.test.core.entities.LatestGood

interface GoodsRepository {

    suspend fun getLatestGoods(): List<LatestGood>

    suspend fun getFlashSaleGoods(): List<FlashSaleGood>

    suspend fun getBrands(): List<Brand>

    suspend fun getKeyWords(): List<String>

    suspend fun getGoodDetails(): GoodDetails
}