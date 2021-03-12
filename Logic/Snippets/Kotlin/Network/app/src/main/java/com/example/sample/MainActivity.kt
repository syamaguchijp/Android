package com.example.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        val query = mutableMapOf<String, String>("username" to "Honda", "password" to "aaaa")
        NetworkManager().get("https://httpbin.org/get", query, { statusCode: Int, responseBody: String ->
            Log.i("response", responseBody)
        })
        */

        NetworkManager().post("https://httpbin.org/post", "AAAA=aaaa&BBBB=bbbb", { statusCode: Int, responseBody: String ->
            Log.i("response", responseBody)
        })


    }
}