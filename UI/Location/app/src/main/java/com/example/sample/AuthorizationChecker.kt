package com.example.sample

import android.Manifest
import android.app.Activity
import android.content.Context
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
    PERMISSION_DENIED
}

class AuthorizationChecker constructor(
    private var context: Context, private var activity: AppCompatActivity
) {

    private var completion: AuthorizationCheckClosure? = null

    fun start(completion: AuthorizationCheckClosure) {

        Logging.d("")

        this.completion = completion

        checkLocationPermission()
    }

    //region 位置情報 権限許諾

    private fun checkLocationPermission() {

        Logging.d("")

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            completion?.let { it(AuthorizationResult.SUCCESS) }
            return
        }
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED ||
            ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
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
            )) {
            // 以前許諾を拒否された場合などの再表示が必要なときにコールされ、ここでアプリが権限を必要とする理由を説明する
            Logging.d("shouldShowRequestPermissionRationale")
            val msg = "XXXXXのため、位置情報を許諾してください。"
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
            if (permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) ||
                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false)) {
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

        return arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
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