package com.test.feature_page1.di

import com.test.data_source.network.GoodsNetworkSource
import com.test.data_source.DataApp
import com.test.feature_page1.data.GoodsRepositoryImpl
import com.test.feature_page1.domain.repositories.GoodsRepository
import dagger.Module
import dagger.Provides

@Module
class PageOneModule {

    @Provides
    fun provideGoodsRepository(
        networkSource: GoodsNetworkSource
    ): GoodsRepository {
        return GoodsRepositoryImpl(networkSource)
    }

    @Provides
    fun providesGoodsNetworkSource(): GoodsNetworkSource = DataApp.getGoodsNetworkSource()
}