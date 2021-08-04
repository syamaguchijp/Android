package com.example.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
    }

    override fun finish() {
        super.finish()
        // 戻るときは、以下のアニメーションが無いほうが自然なので、コメントアウトしておく
        //overridePendingTransition(R.anim.slide_from_top, R.anim.slide_from_bottom)
    }
}