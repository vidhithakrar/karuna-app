package com.pragati.karuna.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pragati.karuna.models.Supplier

class SuppliersViewModel : ViewModel() {
    val suppliers = MutableLiveData<Supplier>()
}

