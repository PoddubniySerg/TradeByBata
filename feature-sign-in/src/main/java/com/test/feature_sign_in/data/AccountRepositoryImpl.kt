package com.test.feature_sign_in.data

import com.test.core.entities.Account
import com.test.core.model.AccountDto
import com.test.feature_sign_in.domain.model.params.CreateAccountParams
import com.test.feature_sign_in.domain.model.params.GetUserByIdParams
import com.test.feature_sign_in.domain.model.params.SaveUserIdParams
import com.test.feature_sign_in.domain.model.params.UserMetricParams
import com.test.feature_sign_in.domain.repositories.AccountRepository

class AccountRepositoryImpl(private val accountRepository: SignInAccountsRepository) : AccountRepository {

    override suspend fun saveUserId(param: SaveUserIdParams) {
        accountRepository.saveCurrentUserId(param.id)
    }

    override suspend fun getUserByMetrics(param: UserMetricParams): Account? {
        return accountRepository.getUserByMetrics(param.firstName, param.email)
    }

    override suspend fun create(param: CreateAccountParams): Account {
        accountRepository.save(
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
        val id = accountRepository.getId(param.firstName, param.lastName, param.email)
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
        accountRepository.update(account)
        return account
    }

    override suspend fun getCurrentUserId(): Int? {
        return accountRepository.getCurrentUserId()
    }

    override suspend fun getUserById(param: GetUserByIdParams): Account? {
        return accountRepository.getUserById(param.id)
    }
}