package com.pragati.karuna.request.repository

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.pragati.karuna.request.model.Supplier

class SuppliersRepository {
    private val db = Firebase.firestore

    fun fetchSuppliers(completionListener: SuppliersCompletionListener) {
        db.collection("supplier").get().addOnSuccessListener { result ->
            completionListener.onComplete(listOf())
            Log.d("Suppliers - ", "$result")
        }.addOnFailureListener { error ->
            completionListener.onError()
            Log.d("Request", "$error")
        }
    }
}

interface SuppliersCompletionListener {
    fun onComplete(supplier: List<Supplier>)
    fun onError()
}