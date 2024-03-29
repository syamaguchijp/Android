package com.example.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MyDialogManager().show(this, "タイトル", "メッセージ",
            {  ->
                println("yes complete!!!")
            },
            {  ->
                println("no complete!!!")
            },
            {  ->
                println("cancel complete!!!")
            })
    }
}