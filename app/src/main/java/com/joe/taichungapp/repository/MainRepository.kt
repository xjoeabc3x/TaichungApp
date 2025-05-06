package com.joe.taichungapp.repository

import com.joe.taichungapp.model.AttractionsInfo
import com.joe.taichungapp.model.FlowerInfo
import com.joe.taichungapp.network.RetrofitClient

class MainRepository {
    suspend fun getFlowers(): List<FlowerInfo> = RetrofitClient.api.getFlowerInfo()
    suspend fun getAttractions(): List<AttractionsInfo> = RetrofitClient.api.getAttractionsInfo()
}