package com.test.domain.usecases

import com.test.domain.model.params.CreateAccountParams
import com.test.domain.model.params.SaveUserIdParams
import com.test.domain.model.params.UserMetricParams
import com.test.domain.model.responses.GoogleAuthorityResponse
import com.test.domain.repository.AccountRepository
import com.test.domain.repository.GoogleRepository
import javax.inject.Inject

open class SignInWithGoogleUseCase @Inject constructor() {

    @Inject
    protected lateinit var googleRepository: GoogleRepository

    @Inject
    protected lateinit var accountRepository: AccountRepository

    suspend fun execute(): GoogleAuthorityResponse {
        val accountFromGoogle =
            googleRepository.authorise() ?: return GoogleAuthorityResponse(account = null)
        val account = accountRepository.getUserByMetrics(
            UserMetricParams(
                accountFromGoogle.firstName,
                accountFromGoogle.email
            )
        ) ?: accountRepository.create(
            CreateAccountParams(
                accountFromGoogle.firstName,
                accountFromGoogle.lastName,
                accountFromGoogle.email
            )
        )
        if (account != null)
            accountRepository.saveUserId(
                SaveUserIdParams(account.id)
            )
        return GoogleAuthorityResponse(account = account)
    }
}