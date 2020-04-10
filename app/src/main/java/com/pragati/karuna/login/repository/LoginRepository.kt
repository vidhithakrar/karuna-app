package com.pragati.karuna.login.repository

import com.google.firebase.auth.FirebaseAuth
import com.pragati.karuna.login.model.LoggedInUser

class LoginRepository(private val auth: FirebaseAuth) {

    fun login(username: String, password: String, completionListener: LoginCompletionListener) {
        auth.signInWithEmailAndPassword(username, password)
            .addOnSuccessListener { result ->
                completionListener.onComplete(result.user?.let {
                    LoggedInUser(
                        it.uid,
                        it.email
                    )
                })
            }.addOnFailureListener {
                completionListener.onComplete(null)
            }
    }
}

interface LoginCompletionListener {
    fun onComplete(user: LoggedInUser?)
}
