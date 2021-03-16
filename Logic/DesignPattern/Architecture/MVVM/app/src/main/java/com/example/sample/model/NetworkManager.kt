package com.example.sample.model

import android.net.Uri
import android.os.Handler
import android.util.Log
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

typealias NetworkManagerClosure = (statusCode: Int, responseBody: String) -> Unit

class NetworkManager {

    // get

    fun get(urlStr: String, map: MutableMap<String, String>, completion: NetworkManagerClosure) {

        val handler = Handler()
        thread {
            // サブスレッドで通信を実行
            val pair = requestGet(urlStr, map)
            handler.post(object: Runnable {
                override fun run() {
                    // 呼び出し元のスレッドに返却
                    Log.i("TAG", "handler post. thread=${Thread.currentThread().name}")
                    completion(pair.first, pair.second)
                }
            })
        }
    }

    private fun requestGet(urlStr: String, map: MutableMap<String, String>): Pair<Int, String> {

        val builder = Uri.Builder()
        for ((key, value) in map) {
            builder.appendQueryParameter(key, value)
        }
        val url = URL(urlStr + builder.toString())
        var responseBody = ""
        var statusCode = 0

        val connection = url.openConnection() as HttpURLConnection
        try {
            connection.connectTimeout = 30000
            connection.readTimeout = 30000
            connection.requestMethod = "GET"
            connection.connect()

            statusCode = connection.responseCode
            if (statusCode == HttpURLConnection.HTTP_OK) {
                responseBody = readStream(connection.inputStream)
            }

        } catch (exception: Exception) {
            Log.e("Error", exception.toString())

        } finally {
            connection.disconnect()
            return Pair(statusCode, responseBody)
        }
    }

    private fun readStream(inputStream: InputStream): String {

        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val responseBody = bufferedReader.use { it.readText() }
        bufferedReader.close()
        return responseBody
    }

}