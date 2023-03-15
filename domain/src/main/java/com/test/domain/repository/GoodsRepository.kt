package com.test.domain.repository

import com.test.domain.entities.Brand
import com.test.domain.entities.FlashSaleGood
import com.test.domain.entities.GoodDetails
import com.test.domain.entities.LatestGood

interface GoodsRepository {

    suspend fun getGoodDetails(): GoodDetails
}