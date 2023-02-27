package com.test.domain.usecases

import com.test.domain.model.params.CreateAccountParams
import com.test.domain.model.params.SaveUserIdParams
import com.test.domain.model.params.UserMetricParams
import com.test.domain.model.responses.AppleAuthorityResponse
import com.test.domain.repository.AccountRepository
import com.test.domain.repository.AppleRepository
import javax.inject.Inject

open class SignInWithAppleUseCase @Inject constructor() {

    @Inject
    protected lateinit var appleRepository: AppleRepository

    @Inject
    protected lateinit var accountRepository: AccountRepository

    suspend fun execute(): AppleAuthorityResponse {
        val accountFromApple =
            appleRepository.authorise() ?: return AppleAuthorityResponse(account = null)
        val account = accountRepository.getUserByMetrics(
            UserMetricParams(
                accountFromApple.firstName,
                accountFromApple.email
            )
        ) ?: accountRepository.create(
            CreateAccountParams(
                accountFromApple.firstName,
                accountFromApple.lastName,
                accountFromApple.email
            )
        )
        if (account != null)
            accountRepository.saveUserId(
                SaveUserIdParams(account.id)
            )
        return AppleAuthorityResponse(account = account)
    }
}