package com.test.feature_page1.domain.usecases

import com.test.feature_page1.domain.model.responses.FlashSaleGoods
import com.test.feature_page1.domain.repositories.GoodsRepository
import javax.inject.Inject

open class GetFlashSaleGoodsUseCase @Inject constructor(private val goodsRepository: GoodsRepository) {

    suspend fun execute(): FlashSaleGoods {
        return FlashSaleGoods(goodsRepository.getFlashSaleGoods())
    }
}