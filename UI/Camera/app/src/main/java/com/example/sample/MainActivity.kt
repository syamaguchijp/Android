package com.example.sample

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {

    companion object {
        val REQUEST_PERMISSION = 10
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        Logging.d("")
        super.onCreate(savedInstanceState)
        setContentView(com.example.sample.R.layout.activity_main)

        val fragment = MainFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(com.example.sample.R.id.constraint_layout, fragment)
        transaction.commit()
    }

}