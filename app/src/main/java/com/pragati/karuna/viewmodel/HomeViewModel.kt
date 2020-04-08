package com.pragati.karuna.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pragati.karuna.models.Family
import com.pragati.karuna.models.Kit
import com.pragati.karuna.models.Location
import com.pragati.karuna.models.Request
import com.pragati.karuna.repository.RequestRepository

class HomeViewModel : ViewModel() {
    private var repository: RequestRepository
    var kit = MutableLiveData<Kit>()
    var families = MutableLiveData<MutableList<Family>>()
    var location = MutableLiveData<Location>()

    init {
        kit.value = Kit()
        families.value = mutableListOf()
        location.value = Location()
        repository = RequestRepository()
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
        var request = Request(
            location.value!!,
            families.value!!,
            kit.value!!
        )
        repository.addRequest(request)
    }
}