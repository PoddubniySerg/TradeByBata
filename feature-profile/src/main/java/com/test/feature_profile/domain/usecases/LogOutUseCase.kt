package com.test.feature_profile.domain.usecases

import com.test.feature_profile.domain.repositories.AccountRepository
import javax.inject.Inject

open class LogOutUseCase @Inject constructor() {

    @Inject
    protected lateinit var accountRepository: AccountRepository

    suspend fun execute() {
        val id = accountRepository.getCurrentUserId()

        if (id != null) {
            accountRepository.removeUserId()
        }

//        accountRepository.remove(
//            RemoveAccountParams(id ?: return)
//        )
    }
}