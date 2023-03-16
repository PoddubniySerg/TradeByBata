package com.test.feature_profile.data

interface ProfileAccountsRepository {

    suspend fun getCurrentUserId(): Int?

    suspend fun removeUserId()

    suspend fun savePhoto(uri: String, content: ByteArray)

    suspend fun remove(id: Int)
}