package com.test.data_source

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.test.core.data.db.AppDatabase
import com.test.data_source.network.GoodsNetworkSource
import com.test.data_source.sharedpreferences.LoginSharedPreferences
import com.test.data_source.sharedpreferences.SharedPreferences
import com.test.data_source.sharedpreferences.SignInSharedPreferences

open class DataApp : Application() {

    companion object {
        private var appContext: Context? = null
        fun getContext() = appContext!!

        private var database: AppDatabase? = null
        fun getLoginDataBase() = database!!

        private var sharedPreferences = SharedPreferences()
        fun getLoginSharedPreferences(): LoginSharedPreferences = sharedPreferences
        fun getSignInSharedPreferences(): SignInSharedPreferences = sharedPreferences

        private var goodsNetworkSource = GoodsNetworkSource()
        fun getGoodsNetworkSource() = goodsNetworkSource
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