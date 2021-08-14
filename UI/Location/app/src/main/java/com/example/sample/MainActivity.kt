package com.example.sample

import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import com.example.sample.Logging.Companion.context
import java.lang.ref.WeakReference

class MainActivity : AppCompatActivity(), LocationObserverCallback {

    var locationObserver: LocationObserver? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        Logging.d("")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        locationObserver = LocationObserver(this, this)
        locationObserver?.let {
            it.callbackRef = WeakReference<LocationObserverCallback>(this)
        }
    }

    override fun onResume() {

        Logging.d("")
        super.onResume()

        locationObserver?.let {
            it.checkPermission()
        }
    }

    // 権限許諾に関する結果がコールバックされる
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>,
                                   grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        Logging.d("")

        if (requestCode != LocationObserver.REQUEST_PERMISSION) {
            return
        }

        if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Logging.d("REQUEST_PERMISSION")
            locationObserver?.let {
                it.startLocation()
            }

        } else {
            Logging.d("REQUEST_PERMISSION DENIED")
            locationObserver?.let {
                it.showSnackBar(this, "「権限」の設定に移行し、位置情報を許可してください。", {
                    val intent = Intent()
                    intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                    val uri: Uri = Uri.fromParts(
                        "package", BuildConfig.APPLICATION_ID, null
                    )
                    intent.data = uri
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                })
            }
        }
    }

    // LocationObserverCallback
    override fun didUpdateLocation(location: Location) {

        Logging.d("")
        Toast.makeText(applicationContext, "${location.latitude} ${location.longitude}", Toast.LENGTH_LONG).show()
    }

}