package com.example.sample

import android.Manifest
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Context.*
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
import com.google.android.material.snackbar.Snackbar

typealias AuthorizationCheckClosure = (result: AuthorizationResult) -> Unit

enum class AuthorizationResult {
    SUCCESS,
    SYSTEM_FAILURE,
    BLUETOOTH_OFF,
    PERMISSION_DENIED
}

class AuthorizationChecker constructor(
    private var context: Context, private var activity: AppCompatActivity) {

    private var completion: AuthorizationCheckClosure? = null

    fun start(completion: AuthorizationCheckClosure) {

        Logging.d("")

        this.completion = completion
        if (!hasSystemFeatureBluetooth()) {
            completion( AuthorizationResult.SYSTEM_FAILURE)
            return
        }
        checkBtOnOff()
    }

    private fun hasSystemFeatureBluetooth(): Boolean {

        if (activity.getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            return true
        }
        return false
    }

    //region Bluetoothのオンオフ状況確認

    private val bluetoothAdapter: BluetoothAdapter? by lazy(LazyThreadSafetyMode.NONE) {
        val bluetoothManager = activity.applicationContext.getSystemService(
            BLUETOOTH_SERVICE) as BluetoothManager
        bluetoothManager.adapter
    }

    private val BluetoothAdapter.isDisabled: Boolean
        get() = !isEnabled

    private fun checkBtOnOff() {

        Logging.d("")

        bluetoothAdapter?.takeIf { it.isDisabled }?.apply {
            Logging.d("launch")
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            btOnRequest.launch(enableBtIntent)
        } ?: requestBtPermission()
    }

    private val btOnRequest = activity.registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        Logging.d("result resultCode=${result}")
        if (result.resultCode == Activity.RESULT_OK) {
            Logging.d("RESULT_OK")
            requestBtPermission()
        } else {
            this?.completion?.invoke(AuthorizationResult.BLUETOOTH_OFF)
        }
    }

    //endregion

    //region Bluetooth 権限許諾

    private val btPermissionLauncher = activity.registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (permissions.getOrDefault(Manifest.permission.BLUETOOTH_SCAN, false) &&
                permissions.getOrDefault(Manifest.permission.BLUETOOTH_CONNECT, false)) {
                checkLocationPermission()
            } else {
                Logging.d("not granted, so return")
                completion?.let { it(AuthorizationResult.PERMISSION_DENIED) }
            }
        } else {
            checkLocationPermission()
        }
    }

    private fun requestBtPermission() {

        Logging.d("")

        btPermissionLauncher.launch(arrayOf(
            Manifest.permission.BLUETOOTH_SCAN, Manifest.permission.BLUETOOTH_CONNECT
        ))
    }

    //endregion

    //region 位置情報 権限許諾

    private fun checkLocationPermission() {

        Logging.d("")

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            completion?.let { it(AuthorizationResult.SUCCESS) }
            return
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q &&
            ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_BACKGROUND_LOCATION
            )
            == PackageManager.PERMISSION_GRANTED) {
            Logging.d("ACCESS_BACKGROUND_LOCATION")
            completion?.let { it(AuthorizationResult.SUCCESS) }
            return
        }
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
            Logging.d("ACCESS_FINE_LOCATION")
            completion?.let { it(AuthorizationResult.SUCCESS) }
            return
        }

        checkLocationPermissionRationale()
    }

    // PermissionRationaleをチェックする
    private fun checkLocationPermissionRationale() {

        Logging.d("")

        if (ActivityCompat.shouldShowRequestPermissionRationale(
                activity, Manifest.permission.ACCESS_FINE_LOCATION
            ) ||
            ActivityCompat.shouldShowRequestPermissionRationale(
                activity, Manifest.permission.ACCESS_COARSE_LOCATION
            ) ||
            ActivityCompat.shouldShowRequestPermissionRationale(
                activity, Manifest.permission.ACCESS_BACKGROUND_LOCATION
            )) {
            // 以前許諾を拒否された場合などの再表示が必要なときにコールされ、ここでアプリが権限を必要とする理由を説明する
            Logging.d("shouldShowRequestPermissionRationale")
            val msg = "iBeacon検知のため、位置情報許諾を「常に許可」「正確な位置情報」にしてください。"
            showSnackBar(activity, msg) {
                requestLocationPermissions()
            }
        } else {
            Logging.d("else")
            requestLocationPermissions()
        }
    }

    private val locationPermissionLauncher = activity.registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            if (permissions.getOrDefault(Manifest.permission.ACCESS_BACKGROUND_LOCATION, false) ||
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false)) {
                completion?.let { it(AuthorizationResult.SUCCESS) }
            } else {
                Logging.d("not granted, so return")
                completion?.let { it(AuthorizationResult.PERMISSION_DENIED) }
            }
        } else {
            completion?.let { it(AuthorizationResult.SUCCESS) }
        }
    }

    private fun requestLocationPermissions() {

        Logging.d("")

        locationPermissionLauncher.launch(requestArray())
    }

    private fun requestArray(): Array<String> {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            // Android10からは、常に許可を求める場合は、ACCESS_BACKGROUND_LOCATIONが必要。
            // targetSdkVersion30以上でFINE_LOCATIONとBACKGROUND_LOCATIONを同時にリクエストするとクラッシュする?
            return arrayOf(Manifest.permission.ACCESS_BACKGROUND_LOCATION)
        }
        return arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,
        )
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