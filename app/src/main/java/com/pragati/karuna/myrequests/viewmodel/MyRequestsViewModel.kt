package com.pragati.karuna.myrequests.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pragati.karuna.myrequests.repository.MyRequestsRepository
import com.pragati.karuna.request.model.Request

class MyRequestsViewModel : ViewModel(), MyRequestsRepository.OnCompletionListener {
    private val repository: MyRequestsRepository = MyRequestsRepository()
    val requests = MutableLiveData<List<Request>>()
    val error = MutableLiveData<String?>()

    fun loadRequests() {
        repository.getRequests(this)
    }

    override fun onComplete(requests: List<Request>) {
        this.requests.value = requests
    }

    override fun onError(error: String?) {
        this.error.value = error
    }
}