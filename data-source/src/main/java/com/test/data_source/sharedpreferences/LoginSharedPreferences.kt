package com.test.data_source.sharedpreferences

interface LoginSharedPreferences {

    suspend fun saveCurrentUserId(id: Int)
}