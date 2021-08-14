package com.example.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        Logging.d("")
        super.onCreate(savedInstanceState)
        setContentView(com.example.sample.R.layout.activity_main)

        val fragment = MainFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(com.example.sample.R.id.constraint_layout, fragment)
        transaction.commit()
    }

}