package com.pragati.karuna.request.repository

import android.util.Log
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.toObject
import com.pragati.karuna.request.model.Family
import com.pragati.karuna.request.model.Request
import java.util.*

class RequestRepository(private val db: FirebaseFirestore) {

    fun addRequest(
        request: Request,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val collection = db.collection(RequestsCollectionName)
        val document = collection.document()
        updateRequest(
            request = request,
            document = document,
            onSuccess = {
                Log.d(Tag, "Request created with id : ${document.id}")
                onSuccess()
            },
            onFailure = { e ->
                Log.e(Tag, "Request creation failed with exception: $e")
                onFailure(e)
            })
    }

    fun updateRequest(
        request: Request,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val document = db.collection("requests").document(request.requestId!!)
        document.collection(FamiliesCollectionName).get()
            .addOnSuccessListener { querySnapShot ->
                db.runBatch {
                    querySnapShot.documents.map { doc ->
                        it.delete(doc.reference)
                    }
                }.addOnSuccessListener {
                    updateRequest(
                        request = request,
                        document = document,
                        onSuccess = {
                            Log.d(Tag, "Request updated with id : ${request.requestId!!}")
                            onSuccess()
                        },
                        onFailure = { e ->
                            Log.e(Tag, "Request update failed with exception: $e")
                            onFailure(e)
                        })
                }.addOnFailureListener(onFailure)
            }
            .addOnFailureListener {
                onFailure(it)
            }
    }

    fun loadRequests(
        uid: String,
        onSuccess: (MutableList<Request>) -> Unit,
        onFailure: (Exception) -> Unit
    ) {

        val requestDaos = mutableListOf<RequestDao>()
        val collection = db.collection("requests")
        val documents = mutableListOf<DocumentReference>()

        collection.whereEqualTo("uid", uid)
            .whereEqualTo("status", Status.CREATED).get()
            .addOnSuccessListener { result ->
                result.forEach { document ->
                    Log.d(Tag, "Request Data: ${document.data}")
                    requestDaos.add(document.toObject())
                    documents.add(document.reference)
                }
                loadFamilies(requestDaos, documents, onSuccess, onFailure)
            }.addOnFailureListener { error ->
                Log.d(Tag, "Requests loading failed with: $error")
                onFailure(error)
            }
    }

    fun closeRequest(id: String, onClosed: () -> Unit, onFailure: (Exception) -> Unit) {
        db.collection(RequestsCollectionName).document(id)
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

    private fun updateRequest(
        request: Request,
        document: DocumentReference,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val requestDao = request.transform(request.uid)
        db.runBatch { batch ->
            batch.set(document, requestDao)
            request.families.forEach { family ->
                val familiesSubCollection = document.collection(FamiliesCollectionName)
                val familyDocument = familiesSubCollection.document()
                batch.set(familyDocument, family)
            }
        }.addOnSuccessListener { onSuccess() }.addOnFailureListener { onFailure(it) }
    }

    private fun loadFamilies(
        requestDaos: MutableList<RequestDao>,
        documents: MutableList<DocumentReference>,
        onSuccess: (MutableList<Request>) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val requests = mutableListOf<Request>()
        val tasks = documents.map {
            it.collection(FamiliesCollectionName).get()
        }
        Tasks.whenAllSuccess<QuerySnapshot>(tasks)
            .addOnSuccessListener { t ->
                t.forEachIndexed { index, querySnapshot ->
                    val families = querySnapshot
                        .map {
                            Log.d(Tag, "Families data: ${it.data}")
                            it.toObject<Family>()
                        }.toMutableList()
                    val request = requestDaos[index].transform(
                        documents[index].id,
                        families
                    )
                    requests.add(request)
                }
                onSuccess(requests)
            }.addOnFailureListener {
                Log.e(Tag, "Error while loading families: $it")
                onFailure(it)
            }
    }

    companion object {
        val Tag = RequestRepository::class.java.name
        const val RequestsCollectionName = "requests"
        const val FamiliesCollectionName = "families"
    }
}

enum class Status {
    CREATED, CLOSED
}

private fun Request.transform(uid: String): RequestDao {
    return RequestDao(
        this.location,
        this.kit,
        this.numberOfKits,
        this.supplierId,
        this.volunteerId,
        uid,
        Status.CREATED,
        if (requestId.isNullOrEmpty()) currentTime() else this.createdTimestamp,
        currentTime()
    )
}

private fun RequestDao.transform(id: String, families: MutableList<Family>): Request {
    return Request(
        requestId = id,
        location = this.location,
        families = families,
        kit = this.kit,
        numberOfKits = this.numberOfKits,
        supplierId = this.supplierId,
        volunteerId = this.volunteerId,
        createdTimestamp = this.createdTimestamp,
        uid = this.uid
    )
}

private fun currentTime(): Long {
    val instance = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
    return instance.timeInMillis
}

