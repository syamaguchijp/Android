package com.example.sample

import android.app.IntentService
import android.content.Intent
import android.content.Context
import android.os.Binder
import android.os.IBinder

// IntentServiceは非同期サブスレッド
class MyIntentService : IntentService("MyIntentService") {

    override fun onCreate() {

        Logging.d("")
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Logging.d("")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onHandleIntent(intent: Intent?) {

        Logging.d("${intent?.getIntExtra("REQUEST_CODE", 0)}")

        for (i in 1..20) {
            Thread.sleep(1000)
            Logging.d("${i}")

            val broadcast = Intent()
            broadcast.putExtra("MY_MESSAGE", "こんにちは ${i}")
            broadcast.setAction("MY_ACTION");
            getBaseContext().sendBroadcast(broadcast);
        }
    }

    override fun onDestroy() {

        Logging.d("")
        super.onDestroy()
        //stopSelf()
    }

}