package com.pragati.karuna.myrequests.repository

import android.os.Handler
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.pragati.karuna.request.model.Request

class MyRequestsRepository {
    private val db = Firebase.firestore
    fun getRequests(completionListener: OnCompletionListener) {
        var requests = mutableListOf<Request>()
        db.collection("requests").get().addOnSuccessListener { result ->
            result.forEach { document ->
                Log.d("Request", "${document.data}")
                requests.add(document.toObject<Request>())
            }
            completionListener.onComplete(requests)
        }.addOnFailureListener { error ->
            Log.d("Error", "${error}")
            completionListener.onError(error.message)
        }
        Handler().postDelayed({
            completionListener.onError("Test test test test")
        }, 50000)
    }

    interface OnCompletionListener {
        fun onComplete(requests: List<Request>)
        fun onError(error: String?)
    }
}