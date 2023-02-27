package com.test.domain.repository

import com.test.domain.entity.Account
import com.test.domain.model.params.*

interface AccountRepository {

    suspend fun getCurrentUserId(): Int?

    suspend fun saveUserId(param: SaveUserIdParams)

    suspend fun removeUserId()

    suspend fun getUserById(param: GetUserByIdParams): Account?

    suspend fun getUserByMetrics(param: UserMetricParams): Account?

    suspend fun create(param: CreateAccountParams): Account?

    suspend fun login(param: LoginParams): Account?

    suspend fun remove(param: RemoveAccountParams)
}