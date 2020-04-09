package com.pragati.karuna.request.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pragati.karuna.request.model.Location

class LocationViewModel : ViewModel() {
    val location = MutableLiveData<Location>()
}