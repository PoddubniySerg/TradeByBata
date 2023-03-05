package com.test.domain.usecases

import com.test.domain.exceptions.SavePhotoException
import com.test.domain.model.params.SavePhotoParams
import com.test.domain.model.requests.Photo
import com.test.domain.model.responses.SavePhotoResponse
import com.test.domain.repository.AccountRepository
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