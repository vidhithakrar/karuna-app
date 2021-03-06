package com.pragati.karuna.myrequests.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pragati.karuna.login.model.LoggedInUser
import com.pragati.karuna.request.model.Request
import com.pragati.karuna.request.repository.RequestRepository

class MyRequestsViewModel(val loggedInUser: LoggedInUser, private val repository: RequestRepository) : ViewModel() {
    val requests = MutableLiveData<List<Request>>()
    val error = MutableLiveData<String?>()

    fun loadRequests() {
        repository.loadRequests(loggedInUser.userId, { requests ->
            this.requests.value = requests
        }, { error ->
            this.error.value = error.message
        })
    }
}