package com.example.geofence

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofenceStatusCodes
import com.google.android.gms.location.GeofencingEvent

class GeofenceBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        Logging.d("")

        intent?.let { context?.let {
            val geofencingEvent = GeofencingEvent.fromIntent(intent)?: return

            if (geofencingEvent.hasError()) {
                val errorMessage = GeofenceStatusCodes
                    .getStatusCodeString(geofencingEvent.errorCode)
                Logging.d(errorMessage)
                return
            }

            val geofenceTransition = geofencingEvent.geofenceTransition
            if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER ||
                geofenceTransition == Geofence.GEOFENCE_TRANSITION_EXIT) {

                val triggeringGeofences = geofencingEvent.triggeringGeofences
                val geofenceTransitionDetails = getTransitionString(
                    geofenceTransition,
                    triggeringGeofences
                )

                this.sendNotification(context, geofenceTransitionDetails)
                Logging.d(geofenceTransitionDetails)
            } else {
                Logging.d("invalid type")
            }
        }}
    }

    private fun getTransitionString(transitionType: Int, triggeringGeofences: List<Geofence>?): String {

        Logging.d("")

        return when (transitionType) {
            Geofence.GEOFENCE_TRANSITION_ENTER -> "ENTER"
            Geofence.GEOFENCE_TRANSITION_EXIT -> "EXIT"
            else -> "ELSE"
        }
    }

    private fun sendNotification(context: Context, message: String) {

        Logging.d("")

        val lnm = LocalNotificationManager()
        lnm.sendNotification(context, "テスト", message, 12345, "NOTIFICATION_LOCAL")
    }
}