package com.example.sample

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.SystemClock

//TODO: does not work after app killed
class AlarmController {

    // static
    companion object {

        private val AlermSpanSec: Long = 60 // 秒

        fun fire(context: Context) {

            Logging.d("")

            val intent = Intent(context, BlScanBroadcastReceiver::class.java)
            val pendingIntent: PendingIntent = PendingIntent.getBroadcast(
                context, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager
            alarmManager?.setExactAndAllowWhileIdle(
                AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + 1000 * AlermSpanSec,
                pendingIntent)

            /*
               alarmManager?.setRepeating(
                   AlarmManager.ELAPSED_REALTIME_WAKEUP,
                   SystemClock.elapsedRealtime(),
                   1000 * AlermSpanSec,
                   pendingIntent
               )
            */
        }
    }
}