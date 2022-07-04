package com.example.sample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class BlScanBroadcastReceiver: BroadcastReceiver() {

    private lateinit var blScanManager: BlScanManager

    private var notifiedFlag = false

    override fun onReceive(context: Context?, intent: Intent?) {

        Logging.d("")

        var context = context ?: return

        // iBeaconをスキャンする
        startScan(context)

        // 次のアラームを設定する
        AlarmController.fire(context)
    }

    private fun startScan(context: Context) {

        blScanManager = BlScanManager(context, {beacon: IBeacon ->
            if (!notifiedFlag) {
                Logging.d("scanned!  ${beacon.major} ${beacon.minor} ${beacon.rssi}")
                LocalNotificationManager().sendNotification(
                    context,
                    "scanned",
                    beacon.uuid,
                    12345,
                    "NOTIFICATION_LOCAL_1"
                )
            }
            notifiedFlag = true
        })
        blScanManager.startScan()
    }

}