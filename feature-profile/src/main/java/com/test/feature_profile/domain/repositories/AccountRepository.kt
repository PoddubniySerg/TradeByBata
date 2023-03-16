package com.test.feature_profile.domain.repositories

import com.test.feature_profile.domain.model.params.RemoveAccountParams
import com.test.feature_profile.domain.model.params.SavePhotoParams

interface AccountRepository {

    suspend fun getCurrentUserId(): Int?

    suspend fun removeUserId()

    suspend fun savePhoto(param: SavePhotoParams)

    suspend fun remove(param: RemoveAccountParams)
}