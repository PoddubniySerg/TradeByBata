package com.test.feature_page1.domain.usecases

import com.test.feature_page1.domain.model.responses.Brands
import com.test.feature_page1.domain.repositories.GoodsRepository
import javax.inject.Inject

open class GetBrandsUseCase @Inject constructor(private val goodsRepository: GoodsRepository) {

    suspend fun execute(): Brands {
        return Brands(goodsRepository.getBrands())
    }
}