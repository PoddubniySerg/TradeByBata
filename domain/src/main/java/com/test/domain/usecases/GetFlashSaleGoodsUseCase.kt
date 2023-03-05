package com.test.domain.usecases

import com.test.domain.model.responses.FlashSaleGoods
import com.test.domain.repository.GoodsRepository
import javax.inject.Inject

open class GetFlashSaleGoodsUseCase @Inject constructor() {

    @Inject
    protected lateinit var goodsRepository: GoodsRepository

    suspend fun execute(): FlashSaleGoods {
        return FlashSaleGoods(goodsRepository.getFlshSaleGoods())
    }
}