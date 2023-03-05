package com.test.data.di

import com.test.data.repository.AccountRepositoryImpl
import com.test.data.repository.GoodsRepositoryImpl
import com.test.domain.repository.AccountRepository
import com.test.domain.repository.GoodsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class Module {

    @Provides
    fun provideAccountRepository(): AccountRepository = AccountRepositoryImpl()

    @Provides
    fun provideGoodsRepository(): GoodsRepository = GoodsRepositoryImpl()
}