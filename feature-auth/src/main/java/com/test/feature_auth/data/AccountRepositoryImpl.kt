package com.test.feature_auth.data

import com.test.core.entities.Account
import com.test.feature_auth.domain.model.LoginParams
import com.test.feature_auth.domain.model.SaveUserIdParams
import com.test.feature_auth.domain.repositories.LoginAccountRepository

open class AccountRepositoryImpl(
    private val accountsRepository: LoginAccountsRepository
) : LoginAccountRepository {

    override suspend fun saveUserId(param: SaveUserIdParams) {
        accountsRepository.saveCurrentUserId(param.id)
    }

    override suspend fun login(param: LoginParams): Account? {
        return accountsRepository.login(param.login, param.password)
    }
}