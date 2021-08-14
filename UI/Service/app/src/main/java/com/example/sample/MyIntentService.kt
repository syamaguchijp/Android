package com.example.sample

import android.app.IntentService
import android.content.Intent
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

        for (i in 1..10) {
            Thread.sleep(1000)
            Logging.d("${i}")

            // Activityへブロードキャストする
            val broadcast = Intent()
            broadcast.putExtra("MY_MESSAGE", "こんにちは ${i}")
            broadcast.setAction("MY_ACTION")
            getBaseContext().sendBroadcast(broadcast)
        }
    }

    override fun onDestroy() {

        Logging.d("")
        super.onDestroy()
        //stopSelf()
    }

    /////////// サービスをServiceConnectionでバインドしてActivityからの情報を受け取る場合は、以下 //////

    private val binder = MyBinder()

    inner class MyBinder : Binder() {
        fun getService(): MyIntentService = this@MyIntentService
    }

    override fun onBind(intent: Intent): IBinder {
        Logging.d("")
        return binder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Logging.d("")
        return super.onUnbind(intent)
    }

    // Activityからの情報を受取る
    fun setMessage(message: String) {
        Logging.d(message)
    }

    //////////////////////////////////////

}