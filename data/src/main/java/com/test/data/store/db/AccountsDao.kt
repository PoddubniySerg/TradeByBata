package com.test.data.store.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.test.data.store.db.dto.AccountDto

@Dao
interface AccountsDao {

    @Query("SELECT * FROM accounts WHERE id= :id")
    suspend fun getUserById(id: Int): AccountDto?

    @Query("SELECT * FROM accounts WHERE first_name = :firstName OR email = :email")
    suspend fun getUserByMetrics(firstName: String, email: String): AccountDto?

    @Query("SELECT * FROM accounts WHERE first_name = :firstName AND password = :password")
    suspend fun login(firstName: String, password: String): AccountDto?

    @Query("SELECT id FROM accounts WHERE first_name = :firstName AND last_name = :lastName AND email = :email")
    suspend fun getId(firstName: String, lastName: String?, email: String): Int

    @Query("SELECT photo_url FROM accounts WHERE id = :id")
    suspend fun getPhotoUri(id: Int): String

    @Insert
    suspend fun save(account: AccountDto)

    @Update
    suspend fun update(account: AccountDto)

    @Query("DELETE FROM accounts WHERE id = :id")
    suspend fun remove(id: Int)
}