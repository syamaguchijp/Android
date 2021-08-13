package com.example.sample

import android.content.pm.PackageManager
import android.net.wifi.ScanResult
import android.net.wifi.WifiInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.lang.ref.WeakReference

class MainActivity : AppCompatActivity(), WifiObserverCallback {

    val wifiObserver = WifiObserver()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wifiObserver.callbackRef = WeakReference<WifiObserverCallback>(this)
    }

    override fun onResume() {

        Logging.d("")

        super.onResume()

        wifiObserver.checkPermission(this, this)
    }

    // 権限許諾に関する結果がコールバックされる
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        Logging.d("")

        if (requestCode != WifiObserver.PERMISSIONS_REQUEST_CODE) {
            return
        }

        if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Logging.d("PERMISSION_GRANTED")
            wifiObserver.startScan(this)
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