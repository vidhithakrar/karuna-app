package com.pragati.karuna.logout.viewmodel

import androidx.lifecycle.ViewModel
import com.pragati.karuna.login.repository.LoginRepository

class LogoutViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    fun logOut() {
        loginRepository.logOut()
    }
}