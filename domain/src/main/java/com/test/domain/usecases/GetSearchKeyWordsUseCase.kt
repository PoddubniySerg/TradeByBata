package com.test.domain.usecases

import com.test.domain.model.requests.SearchRequest
import com.test.domain.model.responses.SearchKeyWords
import com.test.domain.repository.GoodsRepository
import javax.inject.Inject

open class GetSearchKeyWordsUseCase @Inject constructor() {

    @Inject
    protected lateinit var goodsRepository: GoodsRepository

    suspend fun execute(searchRequest: SearchRequest): SearchKeyWords {
        return SearchKeyWords(
            goodsRepository
                .getKeyWords()
                .filter { it.contains(searchRequest.input, ignoreCase = true) }
        )
    }
}