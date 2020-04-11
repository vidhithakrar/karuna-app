package com.pragati.karuna.logout.repository

import com.google.firebase.auth.FirebaseAuth

class LogoutRepository(private val auth: FirebaseAuth) {

    fun signOut() {
        auth.signOut()
    }
}