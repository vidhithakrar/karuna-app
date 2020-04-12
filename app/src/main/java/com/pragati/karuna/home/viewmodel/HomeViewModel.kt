package com.pragati.karuna.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pragati.karuna.R
import com.pragati.karuna.request.model.*
import com.pragati.karuna.request.repository.RequestRepository
import com.pragati.karuna.request.repository.SuppliersRepository
import com.pragati.karuna.request.repository.VolunteerRepository

class HomeViewModel(
    private val repository: RequestRepository,
    private val supplierRepository: SuppliersRepository,
    private val volunteerRepository: VolunteerRepository,
    private val uid: String
) : ViewModel() {
    var supplierId: String? = null
    var volunteerId: String? = null
    var requestId = MutableLiveData<String?>()
    var kit = MutableLiveData<Kit>()
    var families = MutableLiveData<MutableList<Family>>()
    var location = MutableLiveData<Location>()
    var supplier = MutableLiveData<Supplier>()
    var volunteer = MutableLiveData<Volunteer>()
    val requestState = MutableLiveData<RequestState>()

    init {
        families.value = mutableListOf()
    }

    fun addLocation(location: Location) {
        this.location.value = location
    }

    fun updateFamilies(familyList: MutableList<Family>) {
        families.value?.clear()
        families.value = familyList
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

    fun addVolunteers(volunteer: Volunteer) {
        this.volunteer.value = volunteer
    }

    fun addOrUpdateRequest() {
        val request = Request(
            requestId = requestId.value,
            location = location.value!!,
            families = families.value!!,
            kit = kit.value!!,
            supplierId = supplier.value?.id,
            volunteerId = volunteer.value?.id,
            uid = uid
        )
        if (request.requestId.isNullOrEmpty()) {
            repository.addRequest(request, {
                requestState.value =
                    RequestState(RequestState.CREATED, R.string.request_create_success)
            }, {
                requestState.value =
                    RequestState(RequestState.FAILED, R.string.request_create_failure)
            })
        } else {
            repository.updateRequest(request, {
                requestState.value =
                    RequestState(RequestState.UPDATED, R.string.request_update_success)
            }, {
                requestState.value =
                    RequestState(RequestState.FAILED, R.string.request_update_failure)
            })
        }
    }

    fun closeRequest() {
        repository.closeRequest(
            id = requestId.value!!,
            onClosed = {
                requestState.value =
                    RequestState(RequestState.CLOSED, R.string.request_close_success)
            },
            onFailure = {
                requestState.value =
                    RequestState(RequestState.FAILED, R.string.request_close_failure)
            }
        )
    }

    fun fetchSupplier() {
        supplierId?.let { id ->
            supplierRepository.fetchSupplier(
                id = id,
                onSuccess = {
                    supplier.value = it
                },
                onFailure = {
                    requestState.value =
                        RequestState(RequestState.FAILED, R.string.request_close_failure)
                }
            )
        }
    }

    fun fetchVolunteer() {
        volunteerId?.let { id ->
            volunteerRepository.fetchVolunteer(
                id = id,
                onSuccess = {
                    volunteer.value = it
                },
                onFailure = {
                    requestState.value =
                        RequestState(RequestState.FAILED, R.string.request_close_failure)
                }
            )
        }
    }
}