package com.example.sample

import android.content.*
import android.os.Bundle
import android.os.IBinder
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.sample.MyIntentService.MyBinder

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
            startService(intent) //startForegroundService(intent)

            // ServiceConnectionを利用してActivityからIntentServiceに情報を送る場合
            //startIntent()
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

            // ServiceConnectionを利用してActivityからIntentServiceに情報を送る場合
            //stopIntent()
        })
    }

    // IntentServiceからActivityに対してブロードキャストする
    class MyBroadcastReceiver: BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            val extras = intent.getExtras()
            Logging.d(extras?.getString("MY_MESSAGE", ""))
        }
    }

    /////////// ServiceConnectionを利用してActivityからIntentServiceに情報を送る場合は、以下 ////////

    var myService: MyIntentService? = null
    var myIntent: Intent? = null

    var serviceConnection: ServiceConnection = object : ServiceConnection {
        // 接続成功時
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            Logging.d("")
            myService = (service as MyBinder).getService()
            startService(myIntent)
            myService!!.setMessage("Hello!!!!!")
        }
        // 異常切断時
        override fun onServiceDisconnected(name: ComponentName) {
            Logging.d("")
            myService = null
        }
    }

    fun startIntent() {
        Logging.d("")
        myIntent = Intent(application, MyIntentService::class.java)
        bindService(myIntent!!, serviceConnection, Context.BIND_AUTO_CREATE)
    }

    fun stopIntent() {
        Logging.d("")
        unbindService(serviceConnection)
    }

    //////////////////////////////////////

}