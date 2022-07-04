package com.example.map

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.location.*
import java.lang.ref.WeakReference

interface LocationObserverCallback {
    fun didUpdateLocation(location: Location)
}

class LocationObserver constructor(private var context: Context, private var activity: AppCompatActivity) {

    var callbackRef: WeakReference<LocationObserverCallback>? = null // 弱参照

    private val locationClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback

    init {
        Logging.d("")
        locationClient = FusedLocationProviderClient(context)
    }

    fun start() {

        Logging.d("")

        // 位置情報の権限認証を経てから、開始する
        val authorizationController = AuthorizationChecker(context, activity)
        authorizationController.start({ result: AuthorizationResult ->
            print("complete!!!")
            if (result == AuthorizationResult.SUCCESS) {
                startLocation()
            }
        })
    }

    @SuppressLint("MissingPermission")
    private fun startLocation() {

        Logging.d("")

        // 現在の位置情報を取得する
        locationClient.lastLocation.addOnSuccessListener {
            Logging.d("onSuccessListener ${it.latitude} ${it.longitude}")
        }

        // 位置情報の更新を受け取る
        val locationRequest = LocationRequest.create().setPriority(
            Priority.PRIORITY_HIGH_ACCURACY)
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                val location = locationResult.lastLocation
                location?.let {
                    Logging.d("onLocationResult ${location.latitude} ${location.longitude}")
                    val callback = callbackRef?.get()
                    callback?.didUpdateLocation(location)
                }
            }
        }
        locationClient.requestLocationUpdates(locationRequest, locationCallback, null)
    }

    fun stopLocation() {

        Logging.d("")

        // 更新終了する場合
        locationClient.removeLocationUpdates(locationCallback)
    }

}