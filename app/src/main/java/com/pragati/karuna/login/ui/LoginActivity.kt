package com.pragati.karuna.login.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ProgressBar
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.pragati.karuna.R
import com.pragati.karuna.ViewModelFactory
import com.pragati.karuna.home.ui.MainActivity
import com.pragati.karuna.login.model.LoggedInUser
import com.pragati.karuna.login.viewmodel.LoginViewModel
import com.pragati.karuna.util.hideKeyboard
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginViewModel =
            ViewModelProviders.of(
                this,
                ViewModelFactory()
            ).get(LoginViewModel::class.java)

        observeLoginCredentialState(username, password)
        observeLoginState(loading)

        setLoginListener()
    }

    private fun setLoginListener() {
        password.setOnEditorActionListener { _, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_DONE ->
                    login(username, password)
            }
            false
        }

        login.setOnClickListener {
            login(username, password)
        }
    }

    private fun observeLoginCredentialState(username: EditText, password: EditText) {
        loginViewModel.loginCredentialState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            if (!loginState.isDataValid)
                loading.visibility = View.GONE

            if (loginState.usernameError != null) {
                username.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                password.error = getString(loginState.passwordError)
            }
        })
    }

    private fun observeLoginState(loading: ProgressBar) {
        loginViewModel.loginUserState.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer

            loading.visibility = View.GONE
            if (loginResult.error != null) {
                showLoginFailed(loginResult.error)
            }
            if (loginResult.success != null) {
                goToHomeActivity(loginResult.success)
            }
        })
    }

    private fun login(username: EditText, password: EditText) {
        hideKeyboard()
        loading.visibility = View.VISIBLE
        loginViewModel.login(
            username.text.toString(),
            password.text.toString()
        )
    }

    private fun goToHomeActivity(model: LoggedInUser) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        login_error.visibility = View.VISIBLE
        username.text.clear()
        password.text.clear()
        username.requestFocus()
    }
}

