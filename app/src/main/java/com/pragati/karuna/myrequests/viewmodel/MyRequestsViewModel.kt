package com.pragati.karuna.myrequests.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pragati.karuna.request.model.Request

class MyRequestsViewModel : ViewModel() {
    val requests = MutableLiveData<List<Request>>()
}

