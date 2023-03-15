package com.test.feature_page1.domain.usecases

import com.test.feature_page1.domain.model.requests.SearchRequest
import com.test.feature_page1.domain.model.responses.SearchKeyWords
import com.test.feature_page1.domain.repositories.GoodsRepository
import javax.inject.Inject

open class GetSearchKeyWordsUseCase @Inject constructor(private val goodsRepository: GoodsRepository) {

    suspend fun execute(searchRequest: SearchRequest): SearchKeyWords {
        return SearchKeyWords(
            goodsRepository
                .getKeyWords()
                .filter { it.contains(searchRequest.input, ignoreCase = true) }
        )
    }
}