package com.pragati.karuna.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pragati.karuna.R
import com.pragati.karuna.request.model.*
import com.pragati.karuna.request.repository.RequestRepository

class HomeViewModel(private val repository: RequestRepository) : ViewModel() {
    val kit = MutableLiveData<Kit>()
    val families = MutableLiveData<MutableList<Family>>()
    val location = MutableLiveData<Location>()
    val supplier = MutableLiveData<Supplier>()
    val requestState = MutableLiveData<RequestState>()

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

    fun addRequest() {
        val request = Request(
            location = location.value!!,
            families = families.value!!,
            kit = kit.value!!,
            supplierId = supplier.value?.id ?: ""
        )
        repository.addRequest(request)
    }

    fun closeRequest() {
        repository.closeRequest(
            id = "",
            onClosed = {
                requestState.value = RequestState(closed = true)
            },
            onFailure = {
                requestState.value = RequestState(failure = R.string.request_close_failure)
            }
        )
    }
}