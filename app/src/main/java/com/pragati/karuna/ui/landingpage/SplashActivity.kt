package com.pragati.karuna.ui.landingpage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pragati.karuna.MainActivity
import com.pragati.karuna.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val sharedPreference =
            getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE)
        val isFirstTimeInstallation =
            sharedPreference.getBoolean(getString(R.string.isFirstTimeInstallation), true)

        if (!isFirstTimeInstallation)
            navigateToLoginActivity()

        getStartedButton.setOnClickListener {
            if (isFirstTimeInstallation)
                sharedPreference.edit()
                    .putBoolean(getString(R.string.isFirstTimeInstallation), false)
                    .apply()
            navigateToLoginActivity()
        }
    }

    private fun navigateToLoginActivity() {
        // Go to Log in Activity
        // For now, we are directly passing it to MainActivity
        startActivity(Intent(this, MainActivity::class.java))
    }
}
