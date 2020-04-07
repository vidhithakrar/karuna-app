package com.pragati.karuna.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pragati.karuna.models.Family
import com.pragati.karuna.models.Kit
import com.pragati.karuna.models.Location

class HomeViewModel : ViewModel() {
    var kit = MutableLiveData<Kit>()
    var families = MutableLiveData<MutableList<Family>>()
    var location = MutableLiveData<Location>()

    init {
        kit.value = Kit()
        families.value = mutableListOf()
        location.value = Location()
    }
}