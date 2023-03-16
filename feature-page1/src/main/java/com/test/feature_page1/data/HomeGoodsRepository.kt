package com.test.feature_page1.data

import com.test.core.entities.FlashSaleGood
import com.test.core.entities.GoodDetails
import com.test.core.entities.LatestGood

interface HomeGoodsRepository {

    suspend fun getLatestGoods(): List<LatestGood>?

    suspend fun getFlashSaleGoods(): List<FlashSaleGood>?

    suspend fun getKeyWords(): List<String>?

    suspend fun getGoodDetails(): GoodDetails
}
