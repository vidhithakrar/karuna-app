package com.pragati.karuna.ui.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class KitViewModel : ViewModel() {
    private val _kit = MutableLiveData<Kit>()
    var location: LiveData<Kit> = _kit
}