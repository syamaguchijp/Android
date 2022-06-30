package com.example.geofence

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    private lateinit var locationObserver: LocationObserver

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        locationObserver = LocationObserver(applicationContext, this)
        locationObserver.start()
    }
/*
    // locationObserverで実行した権限許諾に関する結果がコールバックされる
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        Logging.d("")

        if (requestCode != LocationObserver.REQUEST_PERMISSION) {
            Logging.d("return")
            return
        }
        locationObserver.reactRequestPermissionsResult(requestCode, permissions, grantResults)
    }
*/
    override fun onResume() {

        Logging.d("")

        super.onResume()
    }

    override fun onPause() {

        Logging.d("")

        super.onPause()
    }
}