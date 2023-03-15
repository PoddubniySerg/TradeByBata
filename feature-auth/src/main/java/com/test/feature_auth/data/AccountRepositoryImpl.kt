package com.test.feature_auth.data

import com.test.core.entities.Account
import com.test.data_source.sharedpreferences.LoginSharedPreferences
import com.test.data_source.db.LoginAccountsDao
import com.test.feature_auth.domain.model.LoginParams
import com.test.feature_auth.domain.model.SaveUserIdParams
import com.test.feature_auth.domain.repositories.LoginAccountRepository

open class AccountRepositoryImpl(
    private val sharedPreferences: LoginSharedPreferences,
    private val accountsDao: LoginAccountsDao
) : LoginAccountRepository {

    override suspend fun saveUserId(param: SaveUserIdParams) {
        sharedPreferences.saveCurrentUserId(param.id)
    }

    override suspend fun login(param: LoginParams): Account? {
        return accountsDao.login(param.login, param.password)
    }
}