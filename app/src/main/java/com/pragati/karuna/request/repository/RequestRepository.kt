package com.pragati.karuna.request.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.pragati.karuna.request.model.Request
import java.util.*

class RequestRepository(private val auth: FirebaseAuth, private val db: FirebaseFirestore) {

    fun addRequest(
        request: Request,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        if (auth.currentUser == null)
            Log.e(Tag, "User is not logged in")
        val requestDao = request.transform(auth.currentUser!!.uid)
        db.collection(CollectionName).add(requestDao).addOnSuccessListener { result ->
            Log.d(Tag, "Request created with id : ${result.id}")
            onSuccess()
        }.addOnFailureListener { error ->
            Log.e(Tag, "Request creation failed with exception: $error")
            onFailure(error)
        }
    }

    fun updateRequest(
        request: Request,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val requestDao = request.transform(auth.currentUser!!.uid)
        db.collection("requests").document(request.requestId!!).set(requestDao)
            .addOnSuccessListener {
                Log.d(Tag, "Request with id updated : ${request.requestId!!}")
                onSuccess()
            }.addOnFailureListener { error ->
                Log.e(Tag, "Request failed with exception: $error")
                onFailure(error)
            }
    }

    fun loadRequests(onSuccess: (MutableList<Request>) -> Unit, onFailure: (Exception) -> Unit) {
        val requests = mutableListOf<Request>()
        db.collection("requests").whereEqualTo("uid", auth.currentUser!!.uid)
            .whereEqualTo("status", Status.CREATED).get()
            .addOnSuccessListener { result ->
                result.forEach { document ->
                    Log.d("Request", "${document.data}")
                    document.toObject<Request>().also { request ->
                        request.requestId = document.id
                        requests.add(request)
                    }
                }
                onSuccess(requests)
            }.addOnFailureListener { error ->
                Log.d("Error", "$error")
                onFailure(error)
            }
    }

    fun closeRequest(id: String, onClosed: () -> Unit, onFailure: (Exception) -> Unit) {
        db.collection(CollectionName).document(id)
            .update(
                mapOf<String, Any>(
                    "status" to Status.CLOSED.name,
                    "modifiedTimestamp" to currentTime()
                )
            )
            .addOnSuccessListener {
                Log.d(Tag, "Request closed with id : $id")
                onClosed()
            }
            .addOnFailureListener { error ->
                Log.e(Tag, "Request closing failed with exception: $error")
                onFailure(error)
            }
    }

    companion object {
        val Tag = RequestRepository::class.java.name
        const val CollectionName = "requests"
    }
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
        this.volunteerId,
        uid,
        Status.CREATED,
        if (requestId.isNullOrEmpty()) currentTime() else this.createdTimestamp,
        currentTime()
    )
}

private fun currentTime(): Long {
    val instance = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
    return instance.timeInMillis
}

