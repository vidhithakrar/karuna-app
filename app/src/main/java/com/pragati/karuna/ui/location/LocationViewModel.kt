package com.pragati.karuna.ui.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pragati.karuna.models.Location

class LocationViewModel : ViewModel() {
    val location = MutableLiveData<Location>()
}