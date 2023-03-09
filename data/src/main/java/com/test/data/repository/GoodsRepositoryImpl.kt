package com.test.data.repository

import com.test.data.exceptions.GoodsNetworkResponseException
import com.test.data.store.network.GoodsNetworkSource
import com.test.data.store.network.dto.BrandDto
import com.test.domain.entities.Brand
import com.test.domain.entities.FlashSaleGood
import com.test.domain.entities.GoodDetails
import com.test.domain.entities.LatestGood
import com.test.domain.repository.GoodsRepository

class GoodsRepositoryImpl : GoodsRepository {

    private val networkSource = GoodsNetworkSource().getLoader()

    override suspend fun getLatestGoods(): List<LatestGood> {
        val response = networkSource.getLatestGoods()
        return response.body()?.latest
            ?: throw GoodsNetworkResponseException("Code:${response.code()}, message: ${response.message()}")
    }

    override suspend fun getFlshSaleGoods(): List<FlashSaleGood> {
        val response = networkSource.getFlashSaleGoods()
        return response.body()?.flashSale
            ?: throw GoodsNetworkResponseException("Code:${response.code()}, message: ${response.message()}")
    }

    override suspend fun getBrands(): List<Brand> {
        return mutableListOf(
            BrandDto("https://www.dhresource.com/0x0/f2/albu/g8/M01/9D/19/rBVaV1079WeAEW-AAARn9m_Dmh0487"),
            BrandDto("https://avatars.mds.yandex.net/get-mpic/6251774/img_id4273297770790914968.jpeg/orig"),
            BrandDto("https://www.tradeinn.com/f/13754/137546834/microsoft-xbox-xbox-one-s-1tb-console-additional-controller.jpg"),
            BrandDto("https://mirbmw.ru/wp-content/uploads/2022/01/manhart-mhx6-700-01.jpg")
        ).toList()
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