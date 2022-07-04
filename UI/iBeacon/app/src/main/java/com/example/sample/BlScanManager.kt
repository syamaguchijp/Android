package com.example.sample

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.os.Handler
import android.os.Looper

typealias BlScanManagerCallback = (iBeacon: IBeacon) -> Unit

class BlScanManager constructor(
    private var context: Context, private var callback: BlScanManagerCallback) {

    private val SCAN_PERIOD_SEC: Long = 10 // 秒
    private var mScanning: Boolean = false
    private var handler = Handler(Looper.getMainLooper())

    private val bluetoothAdapter: BluetoothAdapter? by lazy(LazyThreadSafetyMode.NONE) {
        val bluetoothManager = context.getSystemService(
            Context.BLUETOOTH_SERVICE
        ) as BluetoothManager
        bluetoothManager.adapter
    }

    //region iBeacon検知

    fun startScan() {

        Logging.d("")

        scanLeDevice(true)
    }

    fun stopScan() {

        Logging.d("")

        scanLeDevice(false)
    }

    private fun scanLeDevice(enable: Boolean) {

        Logging.d("enable=${enable}")

        when (enable) {
            true -> {
                // Stops scanning after a pre-defined scan period.
                handler.postDelayed({
                    mScanning = false
                    bluetoothAdapter?.stopLeScan(leScanCallback)
                }, SCAN_PERIOD_SEC * 1000)
                mScanning = true
                bluetoothAdapter?.startLeScan(leScanCallback)
            }
            else -> {
                mScanning = false
                bluetoothAdapter?.stopLeScan(leScanCallback)
            }
        }
    }

    private val leScanCallback = BluetoothAdapter.LeScanCallback { device, rssi, scanRecord ->
        //activity.runOnUiThread {
            val iBeacon = IBeacon(scanRecord, rssi)
            //if (iBeacon.uuid == "XXXXXXXXXXX") {
                callback(iBeacon)
            //}
            //Logging.d("leScanCallback ${it} ${iBeacon.major} ${iBeacon.minor} ${rssi}")
        //}
    }
}