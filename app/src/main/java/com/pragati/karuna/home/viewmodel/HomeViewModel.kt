package com.pragati.karuna.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pragati.karuna.request.model.Family
import com.pragati.karuna.request.model.Kit
import com.pragati.karuna.request.model.Location
import com.pragati.karuna.request.model.Request
import com.pragati.karuna.request.repository.RequestRepository

class HomeViewModel(private val repository: RequestRepository) : ViewModel() {
    var kit = MutableLiveData<Kit>()
    var families = MutableLiveData<MutableList<Family>>()
    var location = MutableLiveData<Location>()

    init {
        families.value = mutableListOf();
    }

    fun addLocation(location: Location) {
        this.location.value = location
    }

    fun addFamily(family: Family) {
        families.value?.add(family)
        families.value = families.value
    }

    fun addKit(kit: Kit) {
        this.kit.value = kit
    }

    fun addRequest() {
        val request = Request(
            location = location.value!!,
            families = families.value!!,
            kit = kit.value!!
        )
        repository.addRequest(request)
    }
}