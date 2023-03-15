package com.test.feature_auth.domain.repositories

import com.test.feature_auth.domain.model.*

interface LoginAccountRepository {

    suspend fun saveUserId(param: SaveUserIdParams)

    suspend fun login(param: LoginParams): com.test.core.entities.Account?
}