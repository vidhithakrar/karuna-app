package com.pragati.karuna.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pragati.karuna.models.Location

class LocationViewModel : ViewModel() {
    val location = MutableLiveData<Location>()
}