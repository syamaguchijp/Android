package com.example.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sample.gson.GsonManager
import com.example.sample.jsonobject.JsonObjectManager
import com.example.sample.kotlinserialization.KotlinSerializationManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GsonManager().test()

        JsonObjectManager().test()

        KotlinSerializationManager().test()
    }
}