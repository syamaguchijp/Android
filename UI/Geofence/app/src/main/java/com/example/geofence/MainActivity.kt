package com.example.geofence

import android.content.pm.PackageManager
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    private lateinit var locationObserver: LocationObserver
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Logging.context = applicationContext
        textView = findViewById(R.id.my_log_textview)
        textView.setMovementMethod(ScrollingMovementMethod())

        //Logging.deleteFile()

        locationObserver = LocationObserver(applicationContext, this)
        locationObserver.start()
    }

    override fun onResume() {

        super.onResume()

        val str = Logging.readFile()
        textView.setText(str)
    }
}