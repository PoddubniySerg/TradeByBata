package com.test.feature_profile.domain.usecases

import com.test.feature_profile.exceptions.SavePhotoException
import com.test.feature_profile.domain.model.params.SavePhotoParams
import com.test.feature_profile.domain.model.requests.Photo
import com.test.feature_profile.domain.repositories.AccountRepository
import javax.inject.Inject

open class SavePhotoUseCase @Inject constructor() {

    @Inject
    protected lateinit var accountRepository: AccountRepository

    suspend fun execute(param: Photo) {
        try {
            accountRepository.savePhoto(
                SavePhotoParams(
                    param.uri,
                    param.content
                )
            )
        } catch (e: Exception) {
            throw SavePhotoException("Photo not saved")
        }
    }
}