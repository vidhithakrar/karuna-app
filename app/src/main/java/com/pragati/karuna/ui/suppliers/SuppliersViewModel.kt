package com.pragati.karuna.ui.suppliers

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pragati.karuna.models.Suppliers

class SuppliersViewModel : ViewModel() {
    val suppliers = MutableLiveData<Suppliers>()
}

