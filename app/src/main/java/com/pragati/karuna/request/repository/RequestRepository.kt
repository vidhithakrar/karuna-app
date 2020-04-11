package com.pragati.karuna.request.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.pragati.karuna.request.model.Request
import java.util.*

class RequestRepository(private val auth: FirebaseAuth, private val db: FirebaseFirestore) {

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

    fun loadRequests(loadRequestsListener: LoadRequestsListener) {
        var requests = mutableListOf<Request>()
        db.collection("requests").whereEqualTo("uid", auth.currentUser!!.uid).get()
            .addOnSuccessListener { result ->
                result.forEach { document ->
                    Log.d("Request", "${document.data}")
                    requests.add(document.toObject<Request>())
                }
                loadRequestsListener.onComplete(requests)
            }.addOnFailureListener { error ->
                Log.d("Error", "${error}")
                loadRequestsListener.onError(error.message)
            }
    }

    companion object {
        val Tag = RequestRepository::class.java.name
    }
}

interface LoadRequestsListener {
    fun onComplete(requests: List<Request>)
    fun onError(error: String?)
}

enum class Status {
    CREATED, CLOSED
}

private fun Request.transform(uid: String): RequestDao {
    return RequestDao(
        this.location,
        this.families,
        this.kit,
        this.supplierId,
        uid,
        Status.CREATED,
        currentTime(),
        currentTime()
    )
}

private fun currentTime(): Long {
    val instance = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
    return instance.timeInMillis
}

