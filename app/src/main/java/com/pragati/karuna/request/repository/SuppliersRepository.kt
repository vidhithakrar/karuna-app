package com.pragati.karuna.request.repository

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.pragati.karuna.request.model.Supplier

class SuppliersRepository {
    private val db = Firebase.firestore
    private val TAG = "SuppliersRepository - "

    fun fetchSuppliers(completionListener: SuppliersCompletionListener) {
        db.collection("suppliers").get().addOnSuccessListener { document ->
            val suppliers = mutableListOf<Supplier>()
            if (document != null) {
                document.documents.forEach { data ->
                    // TODO: uncomment following code and delete hardCoded parsing and constructing hardCodedSupplier code once DB records gets fixed
//                    val supplier = data.toObject<Supplier>()
//                    if (supplier != null) {
//                        suppliers.add(supplier)
//                    }

                    // locality, city, state and mobileNumber fields are missing in DB for few records This needs to be removed
                    val name = data["name"] as String
                    val id = data["id"] as String
                    val supplierType = data["supplier_type"] as String

                    val hardCodedSupplier = Supplier(name, id, "locality", "city", "state", supplierType, "9123456780")
                    suppliers.add(hardCodedSupplier)
                }
                completionListener.onComplete(suppliers)
            } else {
                Log.d(TAG, "No such document")
            }
        }.addOnFailureListener { exception ->
            Log.d(TAG, "get failed with ", exception)
            completionListener.onError()
        }
    }
}

interface SuppliersCompletionListener {
    fun onComplete(supplier: List<Supplier>)
    fun onError()
}