package com.test.data.di

import android.app.Application
import com.test.data.DataApp
import com.test.data.repository.AccountRepositoryImpl
import com.test.data.repository.AppleRepositoryImpl
import com.test.data.repository.GoogleRepositoryImpl
import com.test.data.store.db.AccountsDao
import com.test.domain.repository.AccountRepository
import com.test.domain.repository.AppleRepository
import com.test.domain.repository.GoogleRepository
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
    fun provideGoogleRepository(): GoogleRepository = GoogleRepositoryImpl()

    @Provides
    fun provideAppleRepository(): AppleRepository = AppleRepositoryImpl()
}