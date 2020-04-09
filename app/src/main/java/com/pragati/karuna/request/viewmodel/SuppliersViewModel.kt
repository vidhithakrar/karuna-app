package com.pragati.karuna.request.viewmodel

import androidx.lifecycle.ViewModel
import com.pragati.karuna.request.model.Supplier

class SuppliersViewModel : ViewModel() {
    val suppliers = listOf<Supplier>()
    val taggedSuppliers = listOf<Supplier>()
}

