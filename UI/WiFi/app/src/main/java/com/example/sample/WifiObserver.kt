package com.example.sample

import android.content.Context
import android.net.wifi.ScanResult
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import java.lang.ref.WeakReference

interface WifiObserverCallback {
    fun didGetWifiState(results: List<ScanResult>)
    fun didGetCurrentWifi(wifi: WifiInfo?)
}

class WifiObserver constructor(var context: Context, var activity: AppCompatActivity) {

    var callbackRef: WeakReference<WifiObserverCallback>? = null // 弱参照
    var wifiManager: WifiManager

    init {
        Logging.d("")
        wifiManager = activity.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
    }

    fun start() {

        Logging.d("")
        // 位置情報の権限認証を経てから、ビーコンスキャンのアラームを開始する
        val authorizationController = AuthorizationChecker(context, activity)
        authorizationController.start({ result: AuthorizationResult ->
            print("complete!!!")
            if (result == AuthorizationResult.SUCCESS) {
                startScan()
            }
        })
    }

    private fun startScan() {

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

        Logging.d("")

        val callback = callbackRef?.get()
        val currentWifi = wifiManager?.connectionInfo
        callback?.didGetCurrentWifi(currentWifi)
    }

}