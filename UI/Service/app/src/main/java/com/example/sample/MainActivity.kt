package com.example.sample

import android.content.*
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.widget.Button
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        Logging.d("")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startButton = findViewById<Button>(R.id.button)
        startButton.setOnClickListener {
            // IntentServiceの開始
            val intent = Intent(application, MyIntentService::class.java)
            intent.putExtra("REQUEST_CODE", 100)
            startService(intent)
            //startForegroundService(intent)
        }

        // IntentServiceからのブロードキャストの受け取り
        val receiver = MyBroadcastReceiver()
        val filter = IntentFilter()
        filter.addAction("MY_ACTION")
        registerReceiver(receiver, filter)

        val stopButton = findViewById<Button>(R.id.button2)
        stopButton.setOnClickListener({
            // IntentServiceの終了
            val intent = Intent(application, MyIntentService::class.java)
            stopService(intent)
        })
    }

    // IntentServiceからActivityに対してブロードキャストする
    class MyBroadcastReceiver: BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            val extras = intent.getExtras()
            Logging.d(extras?.getString("MY_MESSAGE", ""))
        }
    }

}