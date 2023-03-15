package com.test.data_source.db

import androidx.room.Dao
import androidx.room.Query
import com.test.data_source.db.dto.AccountDto

@Dao
interface LoginAccountsDao {

    @Query("SELECT * FROM accounts WHERE first_name = :firstName AND password = :password")
    suspend fun login(firstName: String, password: String): AccountDto?
}