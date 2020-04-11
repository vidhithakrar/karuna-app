package com.pragati.karuna

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.pragati.karuna.home.viewmodel.HomeViewModel
import com.pragati.karuna.login.repository.LoginRepository
import com.pragati.karuna.login.viewmodel.LoginViewModel
import com.pragati.karuna.myrequests.viewmodel.MyRequestsViewModel
import com.pragati.karuna.request.repository.RequestRepository

class ViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(
                repository = LoginRepository(
                    FirebaseAuth.getInstance()
                )
            ) as T
        }
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(
                repository = RequestRepository(FirebaseAuth.getInstance(), Firebase.firestore)
            ) as T
        }
        if (modelClass.isAssignableFrom(MyRequestsViewModel::class.java)) {
            return MyRequestsViewModel(
                repository = RequestRepository(FirebaseAuth.getInstance(), Firebase.firestore)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
