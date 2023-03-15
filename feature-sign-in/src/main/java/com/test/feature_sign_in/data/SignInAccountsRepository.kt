package com.test.feature_sign_in.data

import com.test.core.model.AccountDto

interface SignInAccountsRepository {

    suspend fun save(account: AccountDto)

    suspend fun update(account: AccountDto)

    suspend fun getId(firstName: String, lastName: String?, email: String): Int

    suspend fun getUserByMetrics(firstName: String, email: String): AccountDto?

    suspend fun getUserById(id: Int): AccountDto?

    suspend fun saveCurrentUserId(id: Int)

    suspend fun getCurrentUserId(): Int?
}