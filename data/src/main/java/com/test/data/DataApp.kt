package com.test.data

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.test.data.store.db.AppDatabase

open class DataApp : Application() {

    companion object {
        private var appContext: Context? = null
        fun getContext() = appContext!!

        private var database: AppDatabase? = null
        fun getDataBase() = database!!
    }

    override fun onCreate() {
        super.onCreate()

        appContext = this

        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "db"
        ).build()
    }
}