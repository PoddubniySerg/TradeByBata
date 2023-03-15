package com.test.data.di

import com.test.data.DataApp
import com.test.data.repository.AccRepository
import com.test.data.repository.AccountRepositoryImpl
import com.test.data.repository.GoodsRepositoryImpl
import com.test.data.store.db.AccountsDao
import com.test.data.store.files.FilesRepository
import com.test.data.store.network.GoodsNetworkApi
import com.test.data.store.network.GoodsNetworkSource
import com.test.data.store.sharedpreferences.SharedPreferences
import com.test.data.util.AccountUtils
import com.test.domain.repository.AccountRepository
import com.test.domain.repository.GoodsRepository
import com.test.feature_auth.data.LoginAccountsRepository
import com.test.feature_page1.data.HomeGoodsRepository
import com.test.feature_sign_in.data.SignInAccountsRepository
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
    fun provideGoodsRepository(goodsNetworkApi: GoodsNetworkApi): GoodsRepositoryImpl {
        return GoodsRepositoryImpl(goodsNetworkApi)
    }

    @Provides
    fun providesGoodsRepository(goodsRepository: GoodsRepositoryImpl): GoodsRepository {
        return goodsRepository
    }

    @Provides
    fun providesHomeGoodsRepository(goodsRepository: GoodsRepositoryImpl): HomeGoodsRepository {
        return goodsRepository
    }

    @Provides
    fun provideGoodsNetworkApi(): GoodsNetworkApi {
        return GoodsNetworkSource().getLoader()
    }

    @Provides
    fun provideAccRepository(
        sharedPreferences: SharedPreferences,
        accountsDao: AccountsDao,
        fileStorage: FilesRepository,
        accountsUtils: AccountUtils
    ): AccRepository {
        return AccRepository(sharedPreferences, accountsDao, fileStorage, accountsUtils)
    }

    @Provides
    fun provideLoginAccountsRepository(accountRepository: AccRepository): LoginAccountsRepository {
        return accountRepository
    }

    @Provides
    fun provideSignInAccountsRepository(accountRepository: AccRepository): SignInAccountsRepository {
        return accountRepository
    }

    @Provides
    fun provideAccountsDao(): AccountsDao = DataApp.getDataBase().accountDao()
}