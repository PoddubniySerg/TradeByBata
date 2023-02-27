package com.test.domain.usecases

import com.test.domain.model.params.RemoveAccountParams
import com.test.domain.repository.AccountRepository
import javax.inject.Inject

open class LogOutUseCase @Inject constructor() {

    @Inject
    protected lateinit var accountRepository: AccountRepository

    suspend fun execute() {
        val id = accountRepository.getCurrentUserId()

        if (id != null) accountRepository.removeUserId()

        accountRepository.remove(
            RemoveAccountParams(
                id ?: return
            )
        )
    }
}