package com.test.data_source.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.test.data_source.db.dto.AccountDto

@Dao
interface SignInAccountsDao {

    @Insert
    suspend fun save(account: AccountDto)

    @Update
    suspend fun update(account: AccountDto)

    @Query("SELECT id FROM accounts WHERE first_name = :firstName AND last_name = :lastName AND email = :email")
    suspend fun getId(firstName: String, lastName: String?, email: String): Int

    @Query("SELECT * FROM accounts WHERE first_name = :firstName OR email = :email")
    suspend fun getUserByMetrics(firstName: String, email: String): AccountDto?

    @Query("SELECT * FROM accounts WHERE id= :id")
    suspend fun getUserById(id: Int): AccountDto?
}