package com.test.feature_sign_in.domain.repositories

import com.test.core.entities.Account
import com.test.feature_sign_in.domain.model.params.CreateAccountParams
import com.test.feature_sign_in.domain.model.params.GetUserByIdParams
import com.test.feature_sign_in.domain.model.params.SaveUserIdParams
import com.test.feature_sign_in.domain.model.params.UserMetricParams

interface AccountRepository {

    suspend fun saveUserId(param: SaveUserIdParams)

    suspend fun getUserByMetrics(param: UserMetricParams): Account?

    suspend fun create(param: CreateAccountParams): Account?

    suspend fun getCurrentUserId(): Int?

    suspend fun getUserById(param: GetUserByIdParams): Account?
}