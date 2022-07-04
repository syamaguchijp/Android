package com.example.sample

import android.net.wifi.ScanResult
import android.net.wifi.WifiInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.lang.ref.WeakReference

class MainActivity : AppCompatActivity(), WifiObserverCallback {

    private var wifiObserver: WifiObserver? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        Logging.d("")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wifiObserver = WifiObserver(this, this)
        wifiObserver?.let {
            it.callbackRef = WeakReference<WifiObserverCallback>(this)
            it.start()
        }
    }

    // WifiObserverCallback

    override fun didGetWifiState(results: List<ScanResult>) {

        Logging.d("")
        println(results)
    }

    override fun didGetCurrentWifi(wifi: WifiInfo?) {

        Logging.d("")
        println(wifi)
    }
}