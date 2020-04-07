package com.pragati.karuna.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pragati.karuna.models.Family
import com.pragati.karuna.models.Kit
import com.pragati.karuna.models.Location

class HomeViewModel : ViewModel() {
    var location = MutableLiveData<Location>()
    var families = MutableLiveData<MutableList<Family>>()
    var kit = MutableLiveData<Kit>()
}