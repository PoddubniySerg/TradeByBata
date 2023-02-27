package com.test.data.store.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.test.data.store.db.dto.AccountDto

@Dao
interface AccountsDao {

    @Query("SELECT * FROM accounts WHERE id= :id")
    suspend fun getUserById(id: Int): AccountDto?

    @Query("SELECT * FROM accounts WHERE first_name = :firstName OR email = :email")
    suspend fun getUserByMetrics(firstName: String, email: String): AccountDto?

    @Insert
    suspend fun save(account: AccountDto)

    @Query("DELETE FROM accounts WHERE id = :id")
    suspend fun remove(id: Int)
}