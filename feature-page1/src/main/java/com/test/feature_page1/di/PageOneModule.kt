package com.test.feature_page1.di

import com.test.feature_page1.data.HomeGoodsRepository
import com.test.feature_page1.data.GoodsRepositoryImpl
import com.test.feature_page1.domain.repositories.GoodsRepository
import dagger.Module
import dagger.Provides

@Module
class PageOneModule {

    @Provides
    fun provideGoodsRepository(
        networkSource: HomeGoodsRepository
    ): GoodsRepository {
        return GoodsRepositoryImpl(networkSource)
    }
}