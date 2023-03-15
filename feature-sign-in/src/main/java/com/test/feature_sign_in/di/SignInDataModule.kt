package com.test.feature_sign_in.di

import com.test.feature_sign_in.data.AccountRepositoryImpl
import com.test.feature_sign_in.data.SignInAccountsRepository
import com.test.feature_sign_in.domain.repositories.AccountRepository
import dagger.Module
import dagger.Provides

@Module
class SignInDataModule {

    @Provides
    @SignInScope
    fun providesAccountRepository(signInAccountsRepository: SignInAccountsRepository): AccountRepository {
        return AccountRepositoryImpl(signInAccountsRepository)
    }
}