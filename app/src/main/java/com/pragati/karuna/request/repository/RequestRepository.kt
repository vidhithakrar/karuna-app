package com.pragati.karuna.request.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.pragati.karuna.request.model.Request

class RequestRepository(private val auth: FirebaseAuth) {
    private val db = Firebase.firestore

    fun addRequest(request: Request) {
        if (auth.currentUser == null)
            Log.e(Tag, "User is not logged in")
        val requestDao = request.transform(auth.currentUser!!.uid)
        db.collection("requests").add(requestDao).addOnSuccessListener { result ->
            Log.d(Tag, result.id)
        }.addOnFailureListener { error ->
            Log.d(Tag, "$error")
        }
    }

    companion object {
        val Tag = RequestRepository::class.java.name
    }
}

enum class Status {
    CREATED, CLOSED
}

fun Request.transform(uid: String): RequestDao {
    return RequestDao(
        this.location,
        this.families,
        this.kit,
        uid,
        Status.CREATED
    )
}

