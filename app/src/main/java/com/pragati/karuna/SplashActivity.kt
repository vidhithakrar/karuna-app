package com.pragati.karuna

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pragati.karuna.login.ui.LoginActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val sharedPreference =
            getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE)
        val isFirstTimeInstallation =
            sharedPreference.getBoolean(getString(R.string.is_first_time_installation), true)

        if (!isFirstTimeInstallation)
            navigateToLoginActivity()

        getStartedButton.setOnClickListener {
            if (isFirstTimeInstallation)
                sharedPreference.edit()
                    .putBoolean(getString(R.string.is_first_time_installation), false)
                    .apply()
            navigateToLoginActivity()
        }
    }

    private fun navigateToLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
        finish()
    }
}
