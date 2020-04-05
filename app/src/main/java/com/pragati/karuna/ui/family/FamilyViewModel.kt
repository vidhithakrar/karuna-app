package com.pragati.karuna.ui.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FamilyViewModel : ViewModel() {
    private val _families = MutableLiveData<List<Family>>()
    var families: LiveData<List<Family>> = _families
}