package com.pragati.karuna.request.repository

import android.util.Log
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.pragati.karuna.request.model.Supplier

class SuppliersRepository {
    private val db = Firebase.firestore
    private val TAG = "SuppliersRepository - "

    fun fetchSuppliers(completionListener: SuppliersCompletionListener) {
        // Todo: Needs to add 2 filters - fetch docs by supplier type and by city/pincode

        db.collection("suppliers").get().addOnSuccessListener { document ->
            val suppliers = mutableListOf<Supplier>()
            if (document != null) {
                document.documents.forEach { data ->
                    // TODO: uncomment following code and delete hardCoded parsing and constructing hardCodedSupplier code once DB records gets fixed
//                    val supplier = data.toObject<Supplier>()
//                    if (supplier != null) {
//                        suppliers.add(supplier)
//                    }

                    // to be removed once DB gets fix
                    val parsedSupplier = deserializeSupplier(data)
                    suppliers.add(parsedSupplier)
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

    private fun deserializeSupplier(data: DocumentSnapshot): Supplier {
        val name = data["name"] as String
        val id = data["id"] as String
        val supplierType = data["supplier_type"] as String
        val locality = data["locality"] as? String
        val city = data["city"] as String
        val state = data["state"] as? String
        val mobileNumber = data["mobile_number"] as? String

        return Supplier(name, id, locality, city, state, supplierType, mobileNumber)
    }

    fun fetchSupplier(id: String, onSuccess: (Supplier) -> Unit, onFailure: (Exception) -> Unit) {
        val documentRef = db.collection("suppliers").document(id)
        documentRef
            .get()
            .addOnSuccessListener { documentSnapshot ->
                onSuccess(deserializeSupplier(documentSnapshot))
            }.addOnFailureListener(onFailure)
    }
}

interface SuppliersCompletionListener {
    fun onComplete(supplier: List<Supplier>)
    fun onError()
}