package com.example.test1


import android.Manifest
import android.Manifest.permission.ACCESS_BACKGROUND_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.R
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


class LocationObserver constructor(var context: Context, var activity: AppCompatActivity) {

    private val GeofenceId = "A1000"
    private val GeofenceLatitude = 35.79309815333721
    private val GeofenceLongitude = 139.4694900165554
    private val GeofenceRadius = 100.0f // m

    private var geofencingClient: GeofencingClient

    companion object {
        val REQUEST_PERMISSION = 10
    }

    init {
        Logging.d("")
        geofencingClient = LocationServices.getGeofencingClient(activity)
    }

    fun start() {

        Logging.d("")

        checkPermission()
    }

    //region 許諾

    // 位置情報の権限の確認
    fun checkPermission() {

        Logging.d("")

        if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q &&
                    ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_BACKGROUND_LOCATION)
                    != PackageManager.PERMISSION_GRANTED)
            // Android10 では、常に許可でないと、ジオフェンスの登録に失敗するようだ
            ||
            (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                    ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED)
        // Android6 以上は、パーミッション確認が必要
        // Android10 では、常に許可、かつ、正確な位置情報でないと、ジオフェンス登録に失敗するようだ
        ) {
            requestLocationPermission()

        } else {
            startGeofence()
        }
    }

    // 位置情報の権限の許諾を求める。
    // ContextCompat.checkSelfPermission() メソッドが PERMISSION_DENIED を返した場合は、
    // shouldShowRequestPermissionRationale() を呼び出します。
    // このメソッドが true を返した場合は、ユーザーに説明 UI を表示します。
    // この UI で、ユーザーが有効にしようとしている機能が特定の権限を必要とする理由を説明します。
    private fun requestLocationPermission() {

        Logging.d("")

        // shouldShowRequestPermissionRationaleメソッドは、
        // 一度拒否されて「今後表示しない」が選択されてないならtrueを返却する。
        //（必ずしもtrueが返却されるわけではなく、時間的間隔などの要素もあるようだ）
        if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q &&
                    ActivityCompat.shouldShowRequestPermissionRationale(
                        activity, Manifest.permission.ACCESS_BACKGROUND_LOCATION))
            ||
            (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                    ActivityCompat.shouldShowRequestPermissionRationale(
                        activity, Manifest.permission.ACCESS_FINE_LOCATION)))
        {
            // 以前許諾を拒否された場合などの再表示が必要なときにコールされ、ここでアプリが権限を必要とする理由を説明する
            Logging.d("shouldShowRequestPermissionRationale")

            showSnackBar(activity, "ジオフェンス利用のため、位置情報許諾を「常に許可」「正確な位置情報」にしてください。", {
                requestPermissionFineLocation()
                //startActivity(Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS))
            })

        } else {
            Logging.d("else")
            requestPermissionFineLocation()
        }
    }

    private val permissionLauncher = activity.registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { granted: Map<String, Boolean> ->

        Logging.d("")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q &&
            granted[ACCESS_BACKGROUND_LOCATION] == true) {
            Logging.d("granted")
            startGeofence()
        } else if (granted[ACCESS_FINE_LOCATION] == true) {
            Logging.d("granted2")
            startGeofence()
        } else {
            Logging.d("not granted")
        }
    }
/*
    private fun requestPermissionFineLocation() {

        Logging.d("")

        permissionLauncher.launch(requestArray())
    }

    private fun requestArray(): Array<String> {

        var ary = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            // Android10からは、常に許可を求める場合は、ACCESS_BACKGROUND_LOCATIONも必要
            ary += Manifest.permission.ACCESS_BACKGROUND_LOCATION
        }
        return ary
    }
*/


    private fun requestPermissionFineLocation() {

        Logging.d("")

        var requestArray = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            // Android10からは、常に許可を求める場合は、ACCESS_BACKGROUND_LOCATIONも必要
            requestArray += Manifest.permission.ACCESS_BACKGROUND_LOCATION
        }
        ActivityCompat.requestPermissions(
            activity, requestArray, REQUEST_PERMISSION
        )
    }



    // 許諾ダイアログに対する応答時に、呼び出し元のonRequestPermissionsResultコール時に呼ばれる
    fun reactRequestPermissionsResult(requestCode: Int, permissions: Array<String?>,
                                      grantResults: IntArray) {

        Logging.d("")

        if (requestCode != REQUEST_PERMISSION) {
            Logging.d("return")
            return
        }
        if (grantResults.size == 0) {
            return
        }
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Logging.d("PERMISSION_GRANTED")
            // ジオフェンスを開始する
            startGeofence()
        } else {
            Logging.d("PERMISSION_DENIED")
        }
    }

    //endregion

    //region ジオフェンス

    fun startGeofence() {

        Logging.d("")

    }

    private fun makeGeofenceList(): List<Geofence>  {

        Logging.d("")

        var geofenceList = mutableListOf<Geofence>()
        geofenceList.add(
            Geofence.Builder()
                .setRequestId(GeofenceId)
                .setCircularRegion(
                    GeofenceLatitude,
                    GeofenceLongitude,
                    GeofenceRadius
                )
                .setExpirationDuration(NEVER_EXPIRE)
                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER or Geofence.GEOFENCE_TRANSITION_EXIT)
                .build()
        )
        return geofenceList
    }

    private fun getGeofencingRequest(geofenceList: List<Geofence>): GeofencingRequest {

        Logging.d("")

        return GeofencingRequest.Builder().apply {
            // 多くのケースでは、 INITIAL_TRIGGER_DWELL の使用をおすすめします。
            // そうすると、定義された期間が経過するまでユーザーがジオフェンス内にとどまったときに限り、イベントがトリガーされます
            setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
            addGeofences(geofenceList)
        }.build()
    }




    //endregion


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