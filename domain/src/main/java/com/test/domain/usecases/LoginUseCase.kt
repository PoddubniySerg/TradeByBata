package com.test.domain.usecases

import com.test.domain.model.params.LoginParams
import com.test.domain.model.params.SaveUserIdParams
import com.test.domain.model.requests.LoginObject
import com.test.domain.model.responses.LoginResponse
import com.test.domain.repository.AccountRepository
import javax.inject.Inject

open class LoginUseCase @Inject constructor() {

    @Inject
    protected lateinit var accountRepository: AccountRepository

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