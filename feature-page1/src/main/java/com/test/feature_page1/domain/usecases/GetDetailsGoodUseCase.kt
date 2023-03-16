package com.test.feature_page1.domain.usecases

import com.test.feature_page1.domain.model.responses.GoodDetailsResponse
import com.test.feature_page1.domain.repositories.GoodsRepository

import javax.inject.Inject

open class GetDetailsGoodUseCase @Inject constructor() {

    @Inject
    protected lateinit var goodsRepository: GoodsRepository

    suspend fun execute(): GoodDetailsResponse {
        return GoodDetailsResponse(goodsRepository.getGoodDetails())
    }
}