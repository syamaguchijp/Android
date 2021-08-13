package com.example.sample

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Location
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.material.snackbar.Snackbar
import java.lang.ref.WeakReference

interface LocationObserverCallback {
    fun didUpdateLocation(location: Location)
}

class LocationObserver {

    var callbackRef: WeakReference<LocationObserverCallback>? = null // 弱参照

    companion object {
        val REQUEST_PERMISSION = 10
    }

    // 位置情報の権限の確認
    fun checkPermission(context: Context, activity: Activity) {

        Logging.d("")

        if (ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            startLocation(context)

        } else {
            requestLocationPermission(activity)
        }
    }

    fun showSnackBar(activity: Activity, message: String, closure:()->Unit) {

        Logging.d("")

        val rootLayout: View = activity.findViewById(android.R.id.content)
        val snackbar = Snackbar.make(rootLayout, message, Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction("OK") {
            Logging.d("closure")
            closure()
        }
        snackbar.view.setBackgroundColor(Color.GRAY)
        val snackTextView: TextView = snackbar.view.findViewById(com.google.android.material.R.id.snackbar_text)
        snackTextView.setTextColor(Color.WHITE)
        snackTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20.0f)
        val snackActionView: Button = snackbar.view.findViewById(com.google.android.material.R.id.snackbar_action)
        snackActionView.setBackgroundColor(Color.BLACK)
        snackActionView.setTextColor(Color.RED)
        val view = snackbar.view
        val params = view.layoutParams as FrameLayout.LayoutParams
        params.gravity = Gravity.TOP
        view.layoutParams = params
        snackbar.show()
    }

    // 位置情報の権限の許諾を求める
    private fun requestLocationPermission(activity: Activity) {

        Logging.d("")

        if (ActivityCompat.shouldShowRequestPermissionRationale(
                activity, Manifest.permission.ACCESS_FINE_LOCATION)) {
            // 以前許諾を拒否された場合などの再表示が必要なときにコールされ、ここでアプリが権限を必要とする理由を説明する
            Logging.d("shouldShowRequestPermissionRationale")

            showSnackBar(activity, "XXXXXXの理由により位置情報を取得します。", {
                requestPermissionFineLocation(activity)
            })

        } else {
            Logging.d("else")
            requestPermissionFineLocation(activity)
        }
    }

    private fun requestPermissionFineLocation(activity: Activity) {

        Logging.d("")

        ActivityCompat.requestPermissions(
            activity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            REQUEST_PERMISSION
        )
    }

    @SuppressLint("MissingPermission")
    fun startLocation(context: Context) {

        Logging.d("")

        val client = FusedLocationProviderClient(context)

        // 現在の位置情報を取得する
        client.lastLocation.addOnSuccessListener {
            Logging.d("onSuccessListener ${it.latitude} ${it.longitude}")
        }

        // 位置情報の更新を受け取る
        val locationRequest =
            LocationRequest.create().setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
        client.requestLocationUpdates(locationRequest, object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                val location: Location = locationResult.lastLocation
                Logging.d("onLocationResult ${location.latitude} ${location.longitude}")
                val callback = callbackRef?.get()
                callback?.didUpdateLocation(location)
            }
        }, null)
    }
}