package com.test.data_source.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class GoodsNetworkSource {

    private val host = "https://run.mocky.io"

    private val retrofit =
        Retrofit.Builder()
            .baseUrl(host)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    fun getLoader(): GoodsApi {
        return retrofit.create(GoodsApi::class.java)
    }
}