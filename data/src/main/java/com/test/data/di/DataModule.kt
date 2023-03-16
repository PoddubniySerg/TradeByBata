package com.test.data.di

import com.test.data.DataApp
import com.test.data.repository.AccountsRepository
import com.test.data.repository.GoodsRepositoryImpl
import com.test.data.store.db.AccountsDao
import com.test.data.store.files.FilesRepository
import com.test.data.store.network.GoodsNetworkApi
import com.test.data.store.network.GoodsNetworkSource
import com.test.data.store.sharedpreferences.SharedPreferences
import com.test.data.util.AccountUtils
import com.test.feature_auth.data.LoginAccountsRepository
import com.test.feature_page1.data.HomeGoodsRepository
import com.test.feature_profile.data.ProfileAccountsRepository
import com.test.feature_sign_in.data.SignInAccountsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Module
class DataModule {

    @Provides
    @DataScope
    fun provideGoodsRepository(goodsNetworkApi: GoodsNetworkApi): HomeGoodsRepository {
        return GoodsRepositoryImpl(goodsNetworkApi)
    }

    @Provides
    @DataScope
    fun provideGoodsNetworkApi(): GoodsNetworkApi {
        return GoodsNetworkSource().getLoader()
    }

    @Provides
    @DataScope
    fun provideAccRepository(
        sharedPreferences: SharedPreferences,
        accountsDao: AccountsDao,
        fileStorage: FilesRepository,
        accountsUtils: AccountUtils
    ): AccountsRepository {
        return AccountsRepository(sharedPreferences, accountsDao, fileStorage, accountsUtils)
    }

    @Provides
    @DataScope
    fun provideLoginAccountsRepository(accountRepository: AccountsRepository): LoginAccountsRepository {
        return accountRepository
    }

    @Provides
    @DataScope
    fun provideSignInAccountsRepository(accountRepository: AccountsRepository): SignInAccountsRepository {
        return accountRepository
    }

    @Provides
    @DataScope
    fun provideProfileAccountsRepository(accountRepository: AccountsRepository): ProfileAccountsRepository {
        return accountRepository
    }

    @Provides
    @DataScope
    fun provideAccountsDao(): AccountsDao = DataApp.getDataBase().accountDao()
}

@Scope
annotation class DataScope