package com.pragati.karuna.request.viewmodel

import androidx.lifecycle.ViewModel
import com.pragati.karuna.request.model.Supplier
import com.pragati.karuna.request.repository.SuppliersRepository

class SuppliersViewModel : ViewModel() {
    var suppliers = mutableListOf<Supplier>()
    var taggedSuppliers = mutableListOf<Supplier>()


     fun fetchSuppliers() {
        SuppliersRepository().fetchSuppliers()
    }
}

