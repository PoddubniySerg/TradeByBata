package com.test.feature_auth.di

import com.test.feature_auth.data.AccountRepositoryImpl
import com.test.feature_auth.data.LoginAccountsRepository
import com.test.feature_auth.domain.repositories.LoginAccountRepository
import dagger.Module
import dagger.Provides

@Module
class LoginDataModule {

    @Provides
    @LoginScope
    fun provideLoginAccountRepository(
        accountsDao: LoginAccountsRepository
    ): LoginAccountRepository {
        return AccountRepositoryImpl(accountsDao)
    }
}