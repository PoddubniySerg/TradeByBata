package com.test.feature_sign_in.di

import com.test.data_source.DataApp
import com.test.data_source.db.SignInAccountsDao
import com.test.data_source.sharedpreferences.SignInSharedPreferences
import com.test.feature_sign_in.data.AccountRepositoryImpl
import com.test.feature_sign_in.domain.repositories.AccountRepository
import dagger.Module
import dagger.Provides

@Module
class SignInDataModule {

    @Provides
    fun providesAccountRepository(
        sharedPreferences: SignInSharedPreferences,
        accountsDao: SignInAccountsDao
    ): AccountRepository {
        return AccountRepositoryImpl(sharedPreferences, accountsDao)
    }

    @Provides
    fun providesSignInAccountsDao(): SignInAccountsDao {
        return DataApp.getLoginDataBase().signInAccountDao()
    }

    @Provides
    fun providesSignInSharedPreferences(): SignInSharedPreferences {
        return DataApp.getSignInSharedPreferences()
    }
}