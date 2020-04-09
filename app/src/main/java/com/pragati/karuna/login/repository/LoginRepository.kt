package com.pragati.karuna.login.repository

import com.google.firebase.auth.FirebaseAuth
import com.pragati.karuna.login.model.LoggedInUser

class LoginRepository(private val auth: FirebaseAuth) {

    fun login(username: String, password: String, completionListener: LoginCompletionListener) {
        auth.signInWithEmailAndPassword(username, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    completionListener.onComplete(auth.currentUser?.let {
                        LoggedInUser(
                            it.uid,
                            it.email
                        )
                    })
                } else {
                    completionListener.onComplete(null)
                }
            }
    }
}

interface LoginCompletionListener {
    fun onComplete(user: LoggedInUser?)
}
