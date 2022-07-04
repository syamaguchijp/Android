package com.example.sample

import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import java.lang.ref.WeakReference

class MainActivity : AppCompatActivity(), LocationObserverCallback {

    private lateinit var locationObserver: LocationObserver

    override fun onCreate(savedInstanceState: Bundle?) {

        Logging.d("")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        locationObserver = LocationObserver(this, this)
        locationObserver.callbackRef = WeakReference<LocationObserverCallback>(this)
        locationObserver.start()
    }

    // LocationObserverCallback
    override fun didUpdateLocation(location: Location) {

        Logging.d("")
        Toast.makeText(applicationContext, "${location.latitude} ${location.longitude}", Toast.LENGTH_LONG).show()
    }

}