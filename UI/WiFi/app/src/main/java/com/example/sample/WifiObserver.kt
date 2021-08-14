package com.example.sample

import android.Manifest
import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.location.Location
import android.net.wifi.ScanResult
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import java.lang.ref.WeakReference

interface WifiObserverCallback {
    fun didGetWifiState(results: List<ScanResult>)
    fun didGetCurrentWifi(wifi: WifiInfo?)
}

class WifiObserver constructor(var context: Context, var activity: Activity) {

    var callbackRef: WeakReference<WifiObserverCallback>? = null // 弱参照

    companion object {
        val PERMISSIONS_REQUEST_CODE = 10
    }

    var wifiManager: WifiManager

    init {
        Logging.d("")
        wifiManager = activity.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
    }

    fun checkPermission() {

        Logging.d("")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
            (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_WIFI_STATE) !=
                    PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_NETWORK_STATE) !=
                    PackageManager.PERMISSION_GRANTED)
        ){
            requestPermission()

        } else {
            startScan()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun requestPermission() {

        Logging.d("")

        activity.requestPermissions(
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.ACCESS_NETWORK_STATE),
            PERMISSIONS_REQUEST_CODE)
    }

    fun startScan() {

        Logging.d("")

        retrieveScanResult()

        // deprecated (startScan)
        /*
        activity.registerReceiver(wifiScanReceiver, IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION))
        wifiManager!!.startScan()
        */
    }
    /*
    // deprecated (startScan's callback)
    val wifiScanReceiver = object : BroadcastReceiver() {
        @RequiresApi(Build.VERSION_CODES.M)
        override fun onReceive(context: Context, intent: Intent) {
            Logging.d("")
            val success = intent.getBooleanExtra(WifiManager.EXTRA_RESULTS_UPDATED, false)
            if (success) {
                retrieveScanResult()
            }
        }
    }*/

    private fun retrieveScanResult() {

        Logging.d("")

        if (wifiManager?.wifiState == WifiManager.WIFI_STATE_ENABLED) {
            val results = wifiManager?.scanResults
            val callback = callbackRef?.get()
            results?.let {
                callback?.didGetWifiState(it)
            }
            retrieveCurrentWifi()
        }
    }

    private fun retrieveCurrentWifi() {

        val callback = callbackRef?.get()
        val currentWifi = wifiManager?.connectionInfo
        callback?.didGetCurrentWifi(currentWifi)
    }

}