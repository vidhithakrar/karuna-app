package com.pragati.karuna.home.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.phelat.navigationresult.FragmentResultActivity
import com.pragati.karuna.R
import com.pragati.karuna.login.ui.LoginActivity
import com.pragati.karuna.logout.viewmodel.LogoutViewModel
import com.pragati.karuna.logout.viewmodel.LogoutViewModelFactory
import com.pragati.karuna.request.ui.AboutUsActivity

class MainActivity : FragmentResultActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var logoutViewModel: LogoutViewModel

    override fun getNavHostFragmentId(): Int =
        R.id.nav_host_fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_my_requests,
                R.id.nav_gallery,
                R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        logoutViewModel =
            ViewModelProviders.of(this, LogoutViewModelFactory()).get(LogoutViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_about_us -> {
                Intent(this, AboutUsActivity::class.java).also {
                    startActivity(it)
                }
            }
            R.id.action_logout -> {
                Intent(this, LoginActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    .also {
                        startActivity(it)
                        logoutViewModel.logOut()
                        finish()
                    }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
