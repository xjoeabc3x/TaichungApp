
package com.joe.taichungapp.network

import com.joe.taichungapp.model.AttractionsInfo
import com.joe.taichungapp.model.FlowerInfo
import retrofit2.http.GET

interface FlowerApiService {
    @GET("swagger/OpenData/f116d1db-56f7-4984-bad8-c82e383765c0")
    suspend fun getFlowerInfo(): List<FlowerInfo>

    @GET("swagger/OpenData/38476e5e-9288-4b83-bb33-384b1b36c570")
    suspend fun getAttractionsInfo(): List<AttractionsInfo>
}
