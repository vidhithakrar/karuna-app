package com.pragati.karuna.login.viewmodel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pragati.karuna.R
import com.pragati.karuna.login.repository.LoginCompletionListener
import com.pragati.karuna.login.repository.LoginRepository
import com.pragati.karuna.login.model.LoggedInUser

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginCredentialState>()
    val loginCredentialState: LiveData<LoginCredentialState> = _loginForm

    private val _loginResult = MutableLiveData<LoginUserState>()
    val loginUserState: LiveData<LoginUserState> = _loginResult

    fun login(username: String, password: String) {
        if (!validate(username, password))
            return
        else
            doLogin(username, password)
    }

    private fun doLogin(username: String, password: String) {
        loginRepository.login(username, password, object :
            LoginCompletionListener {
            override fun onComplete(user: LoggedInUser?) {
                if (user != null)
                    _loginResult.value =
                        LoginUserState(
                            success = user
                        )
                else
                    _loginResult.value =
                        LoginUserState(error = R.string.login_failed)
            }
        })
    }

    private fun validate(username: String, password: String): Boolean {
        return if (!isUserNameValid(username)) {
            _loginForm.value =
                LoginCredentialState(
                    usernameError = R.string.invalid_username
                )
            false
        } else if (!isPasswordValid(password)) {
            _loginForm.value =
                LoginCredentialState(
                    passwordError = R.string.invalid_password
                )
            false
        } else {
            _loginForm.value =
                LoginCredentialState(isDataValid = true)
            true
        }
    }

    private fun isUserNameValid(username: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(username).matches()
    }

    private fun isPasswordValid(password: String): Boolean {
        return !password.isBlank() && password.length > MaxPasswordLength
    }

    companion object {
        const val MaxPasswordLength = 6
    }
}
