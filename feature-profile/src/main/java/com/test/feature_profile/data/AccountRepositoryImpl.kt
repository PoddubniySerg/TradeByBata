package com.test.feature_profile.data

import com.test.feature_profile.domain.model.params.RemoveAccountParams
import com.test.feature_profile.domain.model.params.SavePhotoParams
import com.test.feature_profile.domain.repositories.AccountRepository

class AccountRepositoryImpl(
    private val accountRepository: ProfileAccountsRepository
) : AccountRepository {

    override suspend fun getCurrentUserId(): Int? {
        return accountRepository.getCurrentUserId()
    }

    override suspend fun removeUserId() {
        accountRepository.removeUserId()
    }

    override suspend fun savePhoto(param: SavePhotoParams) {
        accountRepository.savePhoto(param.uri, param.content)
    }

    override suspend fun remove(param: RemoveAccountParams) {
        accountRepository.remove(param.id)
    }
}