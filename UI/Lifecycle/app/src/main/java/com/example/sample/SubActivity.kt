package com.example.sample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.sample.log.Logging

class SubActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Logging.d("")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        val button: Button = findViewById(R.id.button2)
        button.setOnClickListener {
            finish() // このActivityを破棄して、MainActivityに戻る
        }
    }

    override fun onStart() {
        Logging.d("")
        super.onStart()
    }

    override fun onResume() {
        Logging.d("")
        super.onResume()
    }

    override fun onPause() {
        Logging.d("")
        super.onPause()
    }

    override fun onStop() {
        Logging.d("")
        super.onStop()
    }

    override fun onDestroy() {
        Logging.d("")
        super.onDestroy()
    }

    override fun onRestart() {
        Logging.d("")
        super.onRestart()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Logging.d("")
        super.onActivityResult(requestCode, resultCode, data)
    }
}