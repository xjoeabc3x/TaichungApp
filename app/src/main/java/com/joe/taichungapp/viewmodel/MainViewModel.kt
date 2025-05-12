package com.joe.taichungapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joe.taichungapp.model.AttractionsInfo
import com.joe.taichungapp.model.FlowerInfo
import com.joe.taichungapp.repository.MainRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repository = MainRepository()

    private val _flowers = MutableLiveData<List<FlowerInfo>>()
    val flowers: LiveData<List<FlowerInfo>> = _flowers

    private val _attractions = MutableLiveData<List<AttractionsInfo>>()
    val attractions: LiveData<List<AttractionsInfo>> = _attractions

    var selectedTabIndex: Int = 0

    fun fetchFlowers() {
        viewModelScope.launch {
            try {
                _flowers.value = repository.getFlowers()
            } catch (e: Exception) {
                _flowers.value = emptyList()
            }
        }
    }

    fun fetchAttractions() {
        viewModelScope.launch {
            try {
                _attractions.value = repository.getAttractions()
            } catch (e: Exception) {
                _attractions.value = emptyList()
            }
        }
    }
}