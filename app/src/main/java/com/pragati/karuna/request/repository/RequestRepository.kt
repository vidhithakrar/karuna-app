package com.pragati.karuna.request.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.pragati.karuna.request.model.Request
import java.util.*

class RequestRepository(private val auth: FirebaseAuth, private val db : FirebaseFirestore) {

    fun addRequest(request: Request) {
        if (auth.currentUser == null)
            Log.e(Tag, "User is not logged in")
        val requestDao = request.transform(auth.currentUser!!.uid)
        db.collection("requests").add(requestDao).addOnSuccessListener { result ->
            Log.d(Tag, "Request created with id : ${result.id}")
        }.addOnFailureListener { error ->
            Log.e(Tag, "Request failed with exception: $error")
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
        Status.CREATED,
        currentTime(),
        currentTime()
    )
}

fun currentTime() : Long {
    val instance = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
    return instance.timeInMillis
}

