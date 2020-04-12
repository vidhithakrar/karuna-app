package com.pragati.karuna.logout.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.pragati.karuna.login.repository.LoginRepository

class LogoutViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LogoutViewModel::class.java)) {
            return LogoutViewModel(
                loginRepository = LoginRepository(
                    FirebaseAuth.getInstance()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}