package com.test.core.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.data_source.db.dto.AccountDto
import com.test.data_source.db.LoginAccountsDao
import com.test.data_source.db.SignInAccountsDao

@Database(entities = [AccountDto::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun loginAccountDao(): LoginAccountsDao
    abstract fun signInAccountDao(): SignInAccountsDao
}