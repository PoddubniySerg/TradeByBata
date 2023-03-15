package com.test.feature_page1.domain.usecases

import com.test.feature_page1.domain.model.responses.LatestGoods
import com.test.feature_page1.domain.repositories.GoodsRepository
import javax.inject.Inject

open class GetLatestGoodsUseCase @Inject constructor(private val goodsRepository: GoodsRepository) {

    suspend fun execute(): LatestGoods {
        return LatestGoods(goodsRepository.getLatestGoods())
    }
}