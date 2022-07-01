package com.example.map

import android.Manifest.permission.*
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Location
import android.os.Build
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
import com.google.android.material.snackbar.Snackbar
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

        checkPermission()
    }

    //region 許諾


    // 開始する前に、位置情報権限を確認する
    private fun checkPermission() {

        Logging.d("")

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            startLocation()
            return
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q &&
            ActivityCompat.checkSelfPermission(context, ACCESS_BACKGROUND_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
            startLocation()
            return
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
            (ActivityCompat.checkSelfPermission(context, ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(context, ACCESS_COARSE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED)) {
            startLocation()
            return
        }

        checkPermissionRationale()
    }

    // PermissionRationaleをチェックする
    private fun checkPermissionRationale() {

        Logging.d("")

        if (ActivityCompat.shouldShowRequestPermissionRationale(
                activity, ACCESS_FINE_LOCATION) ||
            ActivityCompat.shouldShowRequestPermissionRationale(
                activity, ACCESS_COARSE_LOCATION) ||
            ActivityCompat.shouldShowRequestPermissionRationale(
                activity, ACCESS_BACKGROUND_LOCATION)) {
            // 以前許諾を拒否された場合などの再表示が必要なときにコールされ、ここでアプリが権限を必要とする理由を説明する
            Logging.d("shouldShowRequestPermissionRationale")
            val msg = "バックグラウンドでの位置情報取得のため、位置情報許諾を「常に許可」「正確な位置情報」にしてください。"
            showSnackBar(activity, msg) {
                requestPermissions()
            }
        } else {
            Logging.d("else")
            requestPermissions()
        }
    }

    private val permissionLauncher = activity.registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            if (permissions.getOrDefault(ACCESS_BACKGROUND_LOCATION, false) ||
                permissions.getOrDefault(ACCESS_FINE_LOCATION, false)) {
                startLocation()
            } else {
                Logging.d("not granted, so return")
            }
        } else {
            startLocation()
        }
    }

    private fun requestPermissions() {

        Logging.d("")

        permissionLauncher.launch(requestArray())
    }

    private fun requestArray(): Array<String> {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            // Android10からは、常に許可を求める場合は、ACCESS_BACKGROUND_LOCATIONが必要。
            // targetSdkVersion30以上でFINE_LOCATIONとBACKGROUND_LOCATIONを同時にリクエストするとクラッシュする?
            return arrayOf(ACCESS_BACKGROUND_LOCATION)
        }
        return arrayOf(
            ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION,
        )
    }

    //endregion

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
    //endregion


    private fun showSnackBar(activity: Activity, message: String, closure:()->Unit) {

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
        snackTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18.0f)
        snackTextView.setMaxLines(20)
        val snackActionView: Button = snackbar.view.findViewById(com.google.android.material.R.id.snackbar_action)
        snackActionView.setBackgroundColor(Color.BLACK)
        snackActionView.setTextColor(Color.WHITE)
        snackActionView.setHeight(50)
        val view = snackbar.view
        val params = view.layoutParams as FrameLayout.LayoutParams
        params.gravity = Gravity.TOP
        view.layoutParams = params
        snackbar.show()
    }

}