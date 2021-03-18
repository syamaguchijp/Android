package com.example.sample

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.example.sample.log.Logging

// ActivityLifecycleCallbacksを使ったライフサイクルの監視
class MyApplication : Application(), Application.ActivityLifecycleCallbacks {

    override fun onCreate() {
        super.onCreate()
        Logging.d("")

        registerActivityLifecycleCallbacks(this)
    }

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        Logging.d(activity?.getLocalClassName())
    }

    override fun onActivityStarted(activity: Activity?) {
        Logging.d(activity?.getLocalClassName())
    }

    override fun onActivityResumed(activity: Activity?) {
        Logging.d(activity?.getLocalClassName())
    }

    override fun onActivityPaused(activity: Activity?) {
        Logging.d(activity?.getLocalClassName())
    }

    override fun onActivityStopped(activity: Activity?) {
        Logging.d(activity?.getLocalClassName())
    }

    override fun onActivityDestroyed(activity: Activity?) {
        Logging.d(activity?.getLocalClassName())
    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
        Logging.d(activity?.getLocalClassName())
    }
}