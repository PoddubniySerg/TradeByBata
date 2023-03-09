package com.test.domain.usecases

import com.test.domain.model.responses.GoodDetailsResponse
import com.test.domain.repository.GoodsRepository
import javax.inject.Inject

open class GetDetailsGoodUseCase @Inject constructor() {

    @Inject
    protected lateinit var goodsRepository: GoodsRepository

    suspend fun execute(): GoodDetailsResponse {
        return GoodDetailsResponse(goodsRepository.getGoodDetails())
    }
}