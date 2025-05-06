
package com.joe.taichungapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val api: FlowerApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://datacenter.taichung.gov.tw/") // Base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FlowerApiService::class.java)
    }
}
