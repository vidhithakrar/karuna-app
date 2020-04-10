package com.pragati.karuna.request.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pragati.karuna.request.model.Supplier
import com.pragati.karuna.request.repository.SuppliersCompletionListener
import com.pragati.karuna.request.repository.SuppliersRepository

class SuppliersViewModel : ViewModel() {
    var suppliers = MutableLiveData<List<Supplier>>()
    var taggedSuppliers = mutableListOf<Supplier>()


     fun fetchSuppliers() {
        SuppliersRepository().fetchSuppliers(object :
            SuppliersCompletionListener {
            override fun onComplete(supplier: List<Supplier>) {
                suppliers.postValue(supplier)
            }

            override fun onError() {
            }
        })
     }
}