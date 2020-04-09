package com.pragati.karuna.request.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pragati.karuna.request.model.Family

class FamilyViewModel : ViewModel() {
    val family = MutableLiveData<Family>()
}