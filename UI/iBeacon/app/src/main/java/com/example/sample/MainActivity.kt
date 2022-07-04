package com.example.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    private lateinit var blAuthorizationManager: BlAuthorizationManager

    override fun onCreate(savedInstanceState: Bundle?) {

        Logging.d("")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Bluetoothの権限認証を経てから、ビーコンスキャンのアラームを開始する
        blAuthorizationManager = BlAuthorizationManager(applicationContext, this)
        blAuthorizationManager.start({ isSuccess: Boolean, result: BlAuthorizationResult ->
            print("complete!!! ${isSuccess}")
            if (isSuccess) {
                AlarmController.fire(applicationContext)
            }
        })
    }

}