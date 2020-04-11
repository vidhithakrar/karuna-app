package com.pragati.karuna.logout.viewmodel

import androidx.lifecycle.ViewModel
import com.pragati.karuna.logout.repository.LogoutRepository

class LogoutViewModel(private val logoutRepository: LogoutRepository) : ViewModel() {

    fun signOut() {
        logoutRepository.signOut()
    }
}