package com.example.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    private lateinit var iBeaconObserver: IBeaconObserver

    override fun onCreate(savedInstanceState: Bundle?) {

        Logging.d("")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        iBeaconObserver = IBeaconObserver(applicationContext, this)
        iBeaconObserver.start()
    }

}