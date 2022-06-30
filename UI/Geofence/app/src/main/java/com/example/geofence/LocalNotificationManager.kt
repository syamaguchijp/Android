package com.example.geofence

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class LocalNotificationManager {

    fun sendNotification(context: Context, title: String, message: String,
                         notificationId: Int, channelId: String) {

        Logging.d(message)

        val builder = NotificationCompat.Builder(context, channelId).apply {
            setSmallIcon(R.drawable.neko)
            setContentTitle(title)
            setContentText(message)
            priority = NotificationCompat.PRIORITY_HIGH
        }

        createNotificationChannel(context, channelId)

        with(NotificationManagerCompat.from(context)) {
            notify(notificationId, builder.build())
        }
    }

    private fun createNotificationChannel(context: Context, channelId: String) {

        Logging.d("")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Android8以上の場合、チャネルに登録する
            val name = "Channel name"
            val descriptionText = "Channel description"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }
            val manager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

}