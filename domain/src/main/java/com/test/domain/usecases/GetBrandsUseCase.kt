package com.test.domain.usecases

import com.test.domain.model.responses.Brands
import com.test.domain.repository.GoodsRepository
import javax.inject.Inject

open class GetBrandsUseCase @Inject constructor() {

    @Inject
    protected lateinit var goodsRepository: GoodsRepository

    suspend fun execute(): Brands {
        return Brands(goodsRepository.getBrands())
    }
}