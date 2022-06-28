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
import androidx.core.app.ActivityCompat
import com.google.android.material.snackbar.Snackbar

class CameraObserver constructor(var context: Context, var activity: Activity)  {

    companion object {
        val REQUEST_PERMISSION = 10
    }

    fun checkCameraPermission() {

        Logging.d("")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
            ActivityCompat.checkSelfPermission(activity, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) {
            requestCameraPermission()
        } else {
            Logging.d("permitted")
        }
    }

    private fun requestCameraPermission() {

        Logging.d("")

        if (ActivityCompat.shouldShowRequestPermissionRationale(
                activity, Manifest.permission.CAMERA)) {
            // 以前許諾を拒否された場合などの再表示が必要なときにコールされ、ここでアプリが権限を必要とする理由を説明する
            Logging.d("shouldShowRequestPermissionRationale")

            showSnackBar(activity, "XXXXXXの理由によりカメラを使用します。", {
                requestPermission()
            })

        } else {
            Logging.d("else")
            requestPermission()
        }
    }

    private fun requestPermission() {

        Logging.d("")

        ActivityCompat.requestPermissions(
            activity, arrayOf(Manifest.permission.CAMERA),
            MainActivity.REQUEST_PERMISSION
        )
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

}