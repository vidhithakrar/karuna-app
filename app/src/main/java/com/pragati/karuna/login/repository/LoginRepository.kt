package com.pragati.karuna.login.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.pragati.karuna.login.model.LoggedInUser

class LoginRepository(private val auth: FirebaseAuth) {

    fun login(username: String, password: String, completionListener: LoginCompletionListener) {
        auth.signInWithEmailAndPassword(username, password)
            .addOnSuccessListener { result ->
                Log.e(Tag, "Login is successful")
                completionListener.onComplete(result.user?.let {
                    LoggedInUser(
                        it.uid,
                        it.email
                    )
                })
            }.addOnFailureListener {
                Log.e(Tag, "Login failed with exception: ${it.message}")
                completionListener.onComplete(null)
            }
    }

    fun logOut() {
        auth.signOut()
    }

    companion object {
        val Tag = LoginRepository::class.java.name
    }
}

interface LoginCompletionListener {
    fun onComplete(user: LoggedInUser?)
}
