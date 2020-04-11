package com.pragati.karuna.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pragati.karuna.request.model.*
import com.pragati.karuna.request.repository.RequestRepository

class HomeViewModel(private val repository: RequestRepository) : ViewModel() {
    var requestId = MutableLiveData<String?>()
    var kit = MutableLiveData<Kit>()
    var families = MutableLiveData<MutableList<Family>>()
    var location = MutableLiveData<Location>()
    var supplier = MutableLiveData<Supplier>()

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

    fun addSuppliers(supplier: Supplier) {
        this.supplier.value = supplier
    }

    fun addOrUpdateRequest() {
        val request = Request(
            requestId = requestId.value,
            location = location.value!!,
            families = families.value!!,
            kit = kit.value!!,
            supplierId = supplier?.value?.id ?: ""
        )
        if (request.requestId.isNullOrEmpty()) {
            repository.addRequest(request)
        } else {
            repository.updateRequest(request)
        }
    }
}