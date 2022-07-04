package com.example.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    private lateinit var blAuthorizationManager: BlAuthorizationManager
    private lateinit var blScanManager: BlScanManager

    override fun onCreate(savedInstanceState: Bundle?) {

        Logging.d("")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        blAuthorizationManager = BlAuthorizationManager(applicationContext, this)
        blAuthorizationManager.start({ isSuccess: Boolean, result: BlAuthorizationResult ->
            print("complete!!! ${isSuccess}")
            if (isSuccess) {
                blScanManager = BlScanManager(applicationContext)
                blScanManager.startScan()
            }
        })
    }

}