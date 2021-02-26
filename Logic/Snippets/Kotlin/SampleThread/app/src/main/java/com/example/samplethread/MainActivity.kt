package com.example.samplethread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.thread
import kotlin.concurrent.withLock


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        
        //runCoroutine()
        //runExclusiveCoroutine()
        //waitCoroutin()

        //runSubThread()
        //runSubThread2()
        //runExclusiveThread()
    }

    //region Coroutine

    // コルーチンによるサブスレッドの実現
    fun runCoroutine() {

        var cnt = 0

        // コルーチン（スコープはGlobalScope）
        GlobalScope.launch(Dispatchers.Default) { // ちなみにDispatchers.Mainにするとメインスレッドとなる。Dispatchers.IOもある
            println(Thread.currentThread())
            println("Coroutine 1")
            delay(1000L) // 1秒スリープ(non-blocking)
            cnt += 1
            println("Coroutine 2 cnt=${cnt}")
        }

        // メインスレッド
        println("UIThread 1")
        cnt += 1
        Thread.sleep(3000L) // 3秒スリープ(blocking)
        println("UIThread 2")
    }

    // メソッドを排他制御
    fun runExclusiveCoroutine() {

        runBlocking {
            repeat(3) { i ->
                launch(Dispatchers.Default) {
                   dumpTest(i)
                }
            }
        }
    }
    val mutex = Mutex() // Mutexによる排他制御で以下のメソッドを排他制御する
    suspend fun dumpTest(i: Int) {
        mutex.withLock {
            println("#$i start. thread=${Thread.currentThread().name}")
            delay(1000)
            println("#$i end.")
        }
    }

    // メインスレッドがコルーチンの終了をWaitingするパターン
    fun waitCoroutin() {

        runBlocking<Unit> {
            val job = GlobalScope.launch {
                println("Coroutine 1")
                delay(1000L)
                println("Coroutine 2")
            }

            // メインスレッド
            println(Thread.currentThread())
            println("UIThread 1")
            job.join() // コルーチンが終了するのをここで待つ
            println("UIThread 2")
        }
    }

    //endregion

    //region Thread

    // Threadでサブスレッドを実現
    fun runSubThread() {

        // サブスレッド  その1
        // kotlin.concurrent.thread を利用
        thread {
            println(Thread.currentThread())
            println("SubThread 1")
            Thread.sleep(1000L) // 1秒スリープ
            println("SubThread 2")
        }

        // メインスレッド
        println("UIThread 1")
        Thread.sleep(3000L) // 3秒スリープ
        println("UIThread 2")
    }

    fun runSubThread2() {

        // サブスレッド その2
        // Runnableを実装した無名クラスで実行
        Thread(object: Runnable {
            override fun run() {
                println(Thread.currentThread())
                println("SubThread 1")
                Thread.sleep(1000L) // 1秒スリープ
                println("SubThread 2")
            }
        }).start()

        // メインスレッド
        println("UIThread 1")
        Thread.sleep(3000L) // 3秒スリープ
        println("UIThread 2")
    }

    // メソッドを排他制御
    fun runExclusiveThread() {

        val lock = ReentrantLock()

        repeat(3 ) {
            thread{
                dumpTest2(lock, it)
            }
        }
    }
    fun dumpTest2(lock: Lock, number: Int) {
        lock.withLock {
            println("#$number start. thread=${Thread.currentThread().name}")
            Thread.sleep(1000)
            println("#$number end.")
        }
    }

    //endregion
}