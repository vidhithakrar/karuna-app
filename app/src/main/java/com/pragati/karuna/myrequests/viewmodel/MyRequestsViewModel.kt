package com.pragati.karuna.myrequests.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pragati.karuna.request.model.Request
import com.pragati.karuna.request.repository.LoadRequestsListener
import com.pragati.karuna.request.repository.RequestRepository

class MyRequestsViewModel(private val repository: RequestRepository) : ViewModel(),
    LoadRequestsListener {
    val requests = MutableLiveData<List<Request>>()
    val error = MutableLiveData<String?>()

    fun loadRequests() {
        repository.loadRequests(this)
    }

    override fun onComplete(requests: List<Request>) {
        this.requests.value = requests
    }

    override fun onError(error: String?) {
        this.error.value = error
    }
}