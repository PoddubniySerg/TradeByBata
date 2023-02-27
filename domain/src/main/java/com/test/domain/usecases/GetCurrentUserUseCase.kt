package com.test.domain.usecases

import com.test.domain.model.params.GetUserByIdParams
import com.test.domain.model.responses.CurrentUser
import com.test.domain.repository.AccountRepository
import javax.inject.Inject

open class GetCurrentUserUseCase @Inject constructor() {

    @Inject
    protected lateinit var accountRepository: AccountRepository

    suspend fun execute(): CurrentUser {
        val id = accountRepository.getCurrentUserId() ?: return CurrentUser(null)
        return CurrentUser(
            accountRepository.getUserById(
                GetUserByIdParams(id)
            )
        )
    }
}