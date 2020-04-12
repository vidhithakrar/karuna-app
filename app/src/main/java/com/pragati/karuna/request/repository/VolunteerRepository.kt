package com.pragati.karuna.request.repository

import android.util.Log
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.pragati.karuna.request.model.Supplier
import com.pragati.karuna.request.model.Volunteer

class VolunteerRepository {
    private val db = Firebase.firestore
    private val TAG = "VolunteerRepository - "

    fun fetchVolunteer(completionListener: VolunteerCompletionListener) {
        // Todo: Needs to add 2 filters - fetch docs by Volunteer type and by city/pincode

        db.collection("volunteers").get().addOnSuccessListener { document ->
            val volunteers = mutableListOf<Volunteer>()
            if (document != null) {
                document.documents.forEach { data ->
                    // TODO: uncomment following code and delete hardCoded parsing and constructing hardCodedVolunteer code once DB records gets fixed
//                    val volunteer = data.toObject<Volunteer>()
//                    if (volunteer != null) {
//                        volunteers.add(volunteer)
//                    }

                    val parsedVolunteer = deserializeVolunteers(data)
                    volunteers.add(parsedVolunteer)
                }
                completionListener.onComplete(volunteers)
            } else {
                Log.d(TAG, "No such document")
            }
        }.addOnFailureListener { exception ->
            Log.d(TAG, "get failed with ", exception)
            completionListener.onError()
        }
    }

    fun fetchVolunteer(id: String, onSuccess: (Volunteer) -> Unit, onFailure: (Exception) -> Unit) {
        val documentRef = db.collection("volunteers").document(id)
        documentRef
            .get()
            .addOnSuccessListener { documentSnapshot ->
                onSuccess(deserializeVolunteers(documentSnapshot))
            }.addOnFailureListener(onFailure)
    }

    private fun deserializeVolunteers(data: DocumentSnapshot): Volunteer {
        val name = data["name"] as String
        val id = data["id"] as String
        val locality = data["locality"] as? String
        val city = data["city"] as? String
        val state = data["state"] as? String
        val mobileNumber = data["mobile_number"] as? String

        return Volunteer(name, id, locality, city, state, mobileNumber)
    }
}

interface VolunteerCompletionListener {
    fun onComplete(volunteer: List<Volunteer>)
    fun onError()
}