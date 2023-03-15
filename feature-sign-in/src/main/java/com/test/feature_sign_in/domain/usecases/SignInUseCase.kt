package com.test.feature_sign_in.domain.usecases

import com.test.feature_sign_in.domain.model.params.CreateAccountParams
import com.test.feature_sign_in.domain.model.params.SaveUserIdParams
import com.test.feature_sign_in.domain.model.params.UserMetricParams
import com.test.feature_sign_in.domain.model.requests.SignInObject
import com.test.feature_sign_in.domain.model.responses.SignInResponse
import com.test.feature_sign_in.domain.repositories.AccountRepository
import com.test.feature_sign_in.exceptions.AccountAlreadyExistException
import javax.inject.Inject

open class SignInUseCase @Inject constructor(private val accountRepository: AccountRepository) {

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