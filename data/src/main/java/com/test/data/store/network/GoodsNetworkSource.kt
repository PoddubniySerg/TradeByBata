package com.test.data.store.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class GoodsNetworkSource {

    private val host = "https://run.mocky.io"

    private val retrofit =
        Retrofit.Builder()
            .baseUrl(host)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    fun getLoader(): GoodsNetworkApi {
        return retrofit.create(GoodsNetworkApi::class.java)
    }
}