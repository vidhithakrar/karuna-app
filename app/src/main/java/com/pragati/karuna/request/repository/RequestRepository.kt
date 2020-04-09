package com.pragati.karuna.request.repository

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.pragati.karuna.request.model.Request

class RequestRepository {
    private val db = Firebase.firestore

    fun addRequest(request: Request) {
        db.collection("requests").add(request).addOnSuccessListener { result ->
            Log.d("Request", "${result.id}")
        }.addOnFailureListener { error ->
            Log.d("Request", "${error}")
        }
    }
}