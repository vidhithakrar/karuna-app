package com.pragati.karuna.login.viewmodel

import com.pragati.karuna.login.model.LoggedInUser

data class LoginUserState(
    val success: LoggedInUser? = null,
    val error: Int? = null
    )

data class LoginCredentialState(
    val usernameError: Int? = null,
    val passwordError: Int? = null,
    val isDataValid: Boolean = false
)
