package com.example.sample

import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.sample.log.Logging

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Logging.d("")
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onStart() {
        Logging.d("")
        super.onStart()
    }

    override fun onResume() {
        Logging.d("")
        super.onResume()
    }

    override fun onPause() {
        Logging.d("")
        super.onPause()
    }

    override fun onStop() {
        Logging.d("")
        super.onStop()
    }

    override fun onDestroy() {
        Logging.d("")
        super.onDestroy()
    }

    override fun onRestart() {
        Logging.d("")
        super.onRestart()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Logging.d("")
        super.onActivityResult(requestCode, resultCode, data)
    }
}