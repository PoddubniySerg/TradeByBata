package com.test.feature_page1.data

import com.test.core.entities.Brand
import com.test.core.entities.FlashSaleGood
import com.test.core.entities.LatestGood
import com.test.feature_page1.domain.repositories.GoodsRepository
import com.test.feature_page1.exceptions.GoodsNetworkResponseException
import com.test.feature_page1.util.BrandsUtil

class GoodsRepositoryImpl(private val networkSource: HomeGoodsRepository) : GoodsRepository {

    override suspend fun getLatestGoods(): List<LatestGood> {
//        val response = loader.getLatestGoods()
//        return response.body()?.latest
//            ?: throw GoodsNetworkResponseException("Code:${response.code()}, message: ${response.message()}")
        return networkSource.getLatestGoods()
            ?: throw GoodsNetworkResponseException("Null value from data")
    }

    override suspend fun getFlashSaleGoods(): List<FlashSaleGood> {
//        val response = loader.getFlashSaleGoods()
//        return response.body()?.flashSale
//            ?: throw GoodsNetworkResponseException("Code:${response.code()}, message: ${response.message()}")
        return networkSource.getFlashSaleGoods()
            ?: throw GoodsNetworkResponseException("Null value from data")
    }

    override suspend fun getBrands(): List<Brand> {
        val brand = BrandsUtil()
        return mutableListOf(
            brand.create("https://www.dhresource.com/0x0/f2/albu/g8/M01/9D/19/rBVaV1079WeAEW-AAARn9m_Dmh0487"),
            brand.create("https://avatars.mds.yandex.net/get-mpic/6251774/img_id4273297770790914968.jpeg/orig"),
            brand.create("https://www.tradeinn.com/f/13754/137546834/microsoft-xbox-xbox-one-s-1tb-console-additional-controller.jpg"),
            brand.create("https://mirbmw.ru/wp-content/uploads/2022/01/manhart-mhx6-700-01.jpg")
        ).toList()
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
//        val response = loader.getKeyWords()
//        return response.body()?.words
//            ?: throw GoodsNetworkResponseException("Code:${response.code()}, message: ${response.message()}")
        return networkSource.getKeyWords()
            ?: throw GoodsNetworkResponseException("Null value from data")
    }
}