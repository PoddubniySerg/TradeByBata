package com.test.feature_auth.data

import com.test.core.model.AccountDto

interface LoginAccountsRepository {

    suspend fun login(firstName: String, password: String): AccountDto?

    suspend fun saveCurrentUserId(id: Int)
}