package com.test.data_source.network

import com.test.data_source.network.dto.FlashSaleDto
import com.test.data_source.network.dto.GoodDetailsDto
import com.test.data_source.network.dto.KeyWordsDto
import com.test.data_source.network.dto.LatestDto
import retrofit2.Response
import retrofit2.http.GET

interface GoodsApi {

    @GET("/v3/cc0071a1-f06e-48fa-9e90-b1c2a61eaca7")
    suspend fun getLatestGoods(): Response<LatestDto>

    @GET("/v3/a9ceeb6e-416d-4352-bde6-2203416576ac")
    suspend fun getFlashSaleGoods(): Response<FlashSaleDto>

    @GET("/v3/f7f99d04-4971-45d5-92e0-70333383c239")
    suspend fun getDetailsGood(): Response<GoodDetailsDto>

    @GET("/v3/4c9cd822-9479-4509-803d-63197e5a9e19")
    suspend fun getKeyWords(): Response<KeyWordsDto>
}