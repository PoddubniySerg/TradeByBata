package com.test.feature_auth.di

import com.test.data_source.DataApp
import com.test.data_source.sharedpreferences.LoginSharedPreferences
import com.test.data_source.db.LoginAccountsDao
import com.test.data_source.sharedpreferences.SharedPreferences
import com.test.feature_auth.data.AccountRepositoryImpl
import com.test.feature_auth.domain.repositories.LoginAccountRepository
import dagger.Module
import dagger.Provides

@Module
class LoginDataModule {

    @Provides
    fun provideLoginAccountRepository(
        sharedPreferences: LoginSharedPreferences,
        accountsDao: LoginAccountsDao
    ): LoginAccountRepository {
        return AccountRepositoryImpl(sharedPreferences, accountsDao)
    }

    @Provides
    fun providesLoginAccountDao(): LoginAccountsDao = DataApp.getLoginDataBase().loginAccountDao()

    @Provides
    fun providesLoginSharedPreferences(): LoginSharedPreferences {
        return DataApp.getLoginSharedPreferences()
    }
}