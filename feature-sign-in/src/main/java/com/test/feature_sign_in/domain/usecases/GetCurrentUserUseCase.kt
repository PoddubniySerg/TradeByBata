package com.test.feature_sign_in.domain.usecases

import com.test.feature_sign_in.domain.model.params.GetUserByIdParams
import com.test.feature_sign_in.domain.model.responses.CurrentUser
import com.test.feature_sign_in.domain.repositories.AccountRepository
import javax.inject.Inject

open class GetCurrentUserUseCase @Inject constructor(private val accountRepository: AccountRepository) {

    suspend fun execute(): CurrentUser {
        val id = accountRepository.getCurrentUserId() ?: return CurrentUser(null)
        return CurrentUser(
            accountRepository.getUserById(
                GetUserByIdParams(id)
            )
        )
    }
}