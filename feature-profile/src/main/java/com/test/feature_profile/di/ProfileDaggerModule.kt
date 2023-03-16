package com.test.feature_profile.di

import com.test.feature_profile.data.AccountRepositoryImpl
import com.test.feature_profile.data.ProfileAccountsRepository
import com.test.feature_profile.domain.repositories.AccountRepository
import dagger.Module
import dagger.Provides

@Module
class ProfileDaggerModule {

    @Provides
    @ProfileScope
    fun providesAccountRepository(accountsRepository: ProfileAccountsRepository): AccountRepository {
        return AccountRepositoryImpl(accountsRepository)
    }
}