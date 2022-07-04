package com.example.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    private lateinit var blAuthorizationManager: AuthorizationChecker

    override fun onCreate(savedInstanceState: Bundle?) {

        Logging.d("")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Bluetoothの権限認証を経てから、ビーコンスキャンのアラームを開始する
        blAuthorizationManager = AuthorizationChecker(applicationContext, this)
        blAuthorizationManager.start({result: AuthorizationResult ->
            print("complete!!!")
            if (result == AuthorizationResult.SUCCESS) {
                AlarmController.fire(applicationContext)
            }
        })
    }

}