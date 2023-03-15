package com.test.feature_sign_in.data

import com.test.core.entities.Account
import com.test.data_source.db.SignInAccountsDao
import com.test.data_source.db.dto.AccountDto
import com.test.data_source.sharedpreferences.SignInSharedPreferences
import com.test.feature_sign_in.domain.model.params.CreateAccountParams
import com.test.feature_sign_in.domain.model.params.GetUserByIdParams
import com.test.feature_sign_in.domain.model.params.SaveUserIdParams
import com.test.feature_sign_in.domain.model.params.UserMetricParams
import com.test.feature_sign_in.domain.repositories.AccountRepository

class AccountRepositoryImpl(
    private val sharedPreferences: SignInSharedPreferences,
    private val accountsDao: SignInAccountsDao
) : AccountRepository {

    override suspend fun saveUserId(param: SaveUserIdParams) {
        sharedPreferences.saveCurrentUserId(param.id)
    }

    override suspend fun getUserByMetrics(param: UserMetricParams): Account? {
        return accountsDao.getUserByMetrics(param.firstName, param.email)
    }

    override suspend fun create(param: CreateAccountParams): Account {
        accountsDao.save(
            AccountDto(
                id = 0,
                firstName = param.firstName,
                lastName = param.lastName,
                password = "",
                photoUrl = param.photoUri,
                email = param.email,
                balance = 0
            )
        )
        val id = accountsDao.getId(param.firstName, param.lastName, param.email)
        val account =
            AccountDto(
                id = id,
                firstName = param.firstName,
                lastName = param.lastName,
                password = "",
                photoUrl = "${param.photoUri}/$id",
                email = param.email,
                balance = 0
            )
        accountsDao.update(account)
        return account
    }

    override suspend fun getCurrentUserId(): Int? {
        return sharedPreferences.getCurrentUserId()
    }

    override suspend fun getUserById(param: GetUserByIdParams): Account? {
        return accountsDao.getUserById(param.id)
    }
}