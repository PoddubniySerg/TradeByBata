package com.test.feature_auth.domain.usecases

import com.test.feature_auth.domain.model.LoginObject
import com.test.feature_auth.domain.model.LoginParams
import com.test.feature_auth.domain.model.LoginResponse
import com.test.feature_auth.domain.model.SaveUserIdParams
import com.test.feature_auth.domain.repositories.LoginAccountRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val accountRepository: LoginAccountRepository) {

    suspend fun execute(param: LoginObject): LoginResponse {
        val account = accountRepository.login(
            LoginParams(param.login, param.password)
        )
        if (account != null)
            accountRepository.saveUserId(
                SaveUserIdParams(account.id)
            )
        return LoginResponse(account)
    }
}