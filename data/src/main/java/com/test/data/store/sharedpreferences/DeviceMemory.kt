package com.test.data.store.sharedpreferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.test.data.DataApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class DeviceMemory {

    companion object {
        private const val USER_ID_KEY = "user_id"
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    }

    private val store get() = DataApp.getContext().dataStore

    suspend fun getCurrentUserId(): Int {
        var id: Int? = null
        CoroutineScope(Dispatchers.IO).launch {
            store.data.collect { preferences ->
                id =
                    preferences[intPreferencesKey(USER_ID_KEY)]
                cancel()
            }
        }.join()
        return id!!
    }

    suspend fun saveCurrentUserId(id: Int) {
        store.edit { preferences ->
            preferences[intPreferencesKey(USER_ID_KEY)] = id
        }
    }

    suspend fun removeCurrentUserId() {
        store.edit { preferences ->
            preferences.remove(intPreferencesKey(USER_ID_KEY))
        }
    }
}