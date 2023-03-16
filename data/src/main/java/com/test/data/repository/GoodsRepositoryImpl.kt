package com.test.data.repository

import com.test.core.entities.FlashSaleGood
import com.test.core.entities.GoodDetails
import com.test.core.entities.LatestGood
import com.test.data.exceptions.GoodsNetworkResponseException
import com.test.data.store.network.GoodsNetworkApi
import com.test.feature_page1.data.HomeGoodsRepository

class GoodsRepositoryImpl(
    private val networkSource: GoodsNetworkApi
) : HomeGoodsRepository {

    override suspend fun getLatestGoods(): List<LatestGood> {
        val response = networkSource.getLatestGoods()
        return response.body()?.latest
            ?: throw GoodsNetworkResponseException("Code:${response.code()}, message: ${response.message()}")
    }

    override suspend fun getFlashSaleGoods(): List<FlashSaleGood> {
        val response = networkSource.getFlashSaleGoods()
        return response.body()?.flashSale
            ?: throw GoodsNetworkResponseException("Code:${response.code()}, message: ${response.message()}")
    }

    override suspend fun getGoodDetails(): GoodDetails {
        val response = networkSource.getDetailsGood()
        return response.body()
            ?: throw GoodsNetworkResponseException("Code:${response.code()}, message: ${response.message()}")
    }

    override suspend fun getKeyWords(): List<String> {
//        заглушка для оффлайн теста
//        return listOf(
//            "Adidas Yeezy",
//            "Sony Playstation",
//            "Nike Air",
//            "Puma",
//            "BMW X6",
//            "Xbox One",
//            "Jack Daniels",
//            "New Balance",
//            "Reebok Classic",
//            "Rolex"
//        )
        val response = networkSource.getKeyWords()
        return response.body()?.words
            ?: throw GoodsNetworkResponseException("Code:${response.code()}, message: ${response.message()}")
    }
}