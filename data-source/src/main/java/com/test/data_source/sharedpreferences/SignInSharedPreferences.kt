package com.test.data_source.sharedpreferences

interface SignInSharedPreferences {

    suspend fun saveCurrentUserId(id: Int)

    suspend fun getCurrentUserId(): Int
}