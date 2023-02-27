package com.test.data.store.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.data.store.db.dto.AccountDto

@Database(entities = [AccountDto::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun accountDao(): AccountsDao
}