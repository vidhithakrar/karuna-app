package com.pragati.karuna.ui.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LocationViewModel : ViewModel() {
    private val _location = MutableLiveData<Kit>()
    var location: LiveData<Kit> = _location
}