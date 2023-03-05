package com.test.domain.usecases

import com.test.domain.exceptions.AccountAlreadyExistException
import com.test.domain.model.params.CreateAccountParams
import com.test.domain.model.params.SaveUserIdParams
import com.test.domain.model.params.UserMetricParams
import com.test.domain.model.requests.SignInObject
import com.test.domain.model.responses.SignInResponse
import com.test.domain.repository.AccountRepository
import javax.inject.Inject

open class SignInUseCase @Inject constructor() {

    @Inject
    protected lateinit var accountRepository: AccountRepository

    suspend fun execute(param: SignInObject): SignInResponse {
        val accountFromRepository = accountRepository.getUserByMetrics(
            UserMetricParams(
                param.firstName,
                param.email
            )
        )
        if (accountFromRepository != null) throw AccountAlreadyExistException("This first name or email exist")
        val account = accountRepository.create(
            CreateAccountParams(
                param.firstName,
                param.lastName,
                param.email,
                param.photoUri
            )
        )
        if (account != null)
            accountRepository.saveUserId(
                SaveUserIdParams(account.id)
            )

        return SignInResponse(account)
    }
}