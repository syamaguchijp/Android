package com.example.sample

import android.app.Application
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import com.example.sample.log.Logging

// ProcessLifecycleOwnerを使ったライフサイクルの監視
class MyApplication2: Application(), LifecycleObserver {

    override fun onCreate() {
        Logging.d("")
        super.onCreate()

        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onApplicationCreate() {
        Logging.d("")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onApplicationStart() {
        Logging.d("")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onApplicationResume() {
        Logging.d("")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onApplicationPause() {
        Logging.d("")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onApplicationStop() {
        Logging.d("")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onApplicationDestroy() {
        Logging.d("")
    }
}