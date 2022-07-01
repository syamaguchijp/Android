package com.example.geofence

import android.Manifest.permission.*
import android.app.Activity
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
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
import com.google.android.gms.location.Geofence.NEVER_EXPIRE
import com.google.android.material.snackbar.Snackbar


class LocationObserver constructor(
    private var context: Context, private var activity: AppCompatActivity) {

    private val GeofenceId = "A1000"
    private val GeofenceLatitude = 35.72895914395796
    private val GeofenceLongitude = 139.7105163770693
    private val GeofenceRadius = 100.0f // m

    private var geofencingClient: GeofencingClient

    init {
        Logging.d("")
        geofencingClient = LocationServices.getGeofencingClient(activity)
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
            startGeofence()
            return
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q &&
            ActivityCompat.checkSelfPermission(context, ACCESS_BACKGROUND_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
            // Android10 では、常に許可でないと、ジオフェンスの登録に失敗する
            startGeofence()
            return
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q &&
            ActivityCompat.checkSelfPermission(context, ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
            // 「正確な位置情報」でないと、ジオフェンスの登録に失敗する
            startGeofence()
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
            val msg = "ジオフェンス利用のため、位置情報許諾を「常に許可」「正確な位置情報」にしてください。"
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
                startGeofence()
            } else {
                Logging.d("not granted, so return")
            }
        } else {
            startGeofence()
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

    //region ジオフェンス

    // ジオフェンスを開始する。
    // 位置情報権限が「常に許可」かつ「位置情報」でないと、addOnFailureListenerがコールされて失敗する。
    private fun startGeofence() {

        Logging.d("")

        val geofenceList = makeGeofenceList()
        val request = getGeofencingRequest(geofenceList)
        geofencingClient.addGeofences(request, getPendingIntent()).run {
            addOnSuccessListener {
                Logging.d("add success")
            }
            addOnFailureListener {
                Logging.d("add failure")
            }
        }
    }

    private fun makeGeofenceList(): List<Geofence>  {

        Logging.d("")

        val geofenceList = mutableListOf<Geofence>()
        geofenceList.add(
            Geofence.Builder()
                .setRequestId(GeofenceId)
                .setCircularRegion(
                    GeofenceLatitude,
                    GeofenceLongitude,
                    GeofenceRadius
                )
                .setExpirationDuration(NEVER_EXPIRE)
                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER or
                        Geofence.GEOFENCE_TRANSITION_EXIT)
                .build()
        )
        return geofenceList
    }

    private fun getGeofencingRequest(geofenceList: List<Geofence>): GeofencingRequest {

        Logging.d("")

        return GeofencingRequest.Builder().apply {
            setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_DWELL)
            addGeofences(geofenceList)
        }.build()
    }

    private fun getPendingIntent(): PendingIntent {

        Logging.d("")

        val intent = Intent(activity, GeofenceBroadcastReceiver::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            return PendingIntent.getBroadcast(activity, 0, intent,
                PendingIntent.FLAG_MUTABLE)
        }
        return PendingIntent.getBroadcast(activity, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT)
    }

    fun stopGeofence() {

        Logging.d("")

        geofencingClient.removeGeofences(getPendingIntent()).run {
            addOnSuccessListener {
                Logging.d("remove geofences success")
            }
            addOnFailureListener {
                Logging.d("remove geofences fail")
            }
        }
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