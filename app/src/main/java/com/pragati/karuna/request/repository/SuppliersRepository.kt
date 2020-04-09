package com.pragati.karuna.request.repository

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SuppliersRepository {
    private val db = Firebase.firestore

    fun fetchSuppliers() {
        db.collection("supplier").get().addOnSuccessListener { result ->
            Log.d("Suppliers - ", "$result")
        }.addOnFailureListener { error ->
            Log.d("Request", "$error")
        }
    }
}