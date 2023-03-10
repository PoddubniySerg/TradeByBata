package com.test.domain.usecases

import com.test.domain.model.responses.LatestGoods
import com.test.domain.repository.GoodsRepository
import javax.inject.Inject

open class GetLatestGoodsUseCase @Inject constructor() {

    @Inject
    protected lateinit var goodsRepository: GoodsRepository

    suspend fun execute(): LatestGoods {
        return LatestGoods(goodsRepository.getLatestGoods())
    }
}