package com.example.sample

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button1 = findViewById<Button>(R.id.button)
        button1.setOnClickListener {
            showSnackBar()
        }

        val button2 = findViewById<Button>(R.id.button2)
        button2.setOnClickListener {
            showSnackBarFromTop()
        }
    }

    fun showSnackBar() {

        val snackbar = generateSnackBar()
        snackbar.show()
    }

    fun showSnackBarFromTop() {

        val snackbar = generateSnackBar()
        val view = snackbar.view
        val params = view.layoutParams as FrameLayout.LayoutParams
        params.gravity = Gravity.TOP
        view.layoutParams = params
        snackbar.show()
    }

    fun generateSnackBar(): Snackbar {

        val rootLayout: View = findViewById(android.R.id.content)
        val snackbar = Snackbar.make(rootLayout, "テストです", Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction("Click") {
            snackbar.dismiss()
        }
        snackbar.view.setBackgroundColor(Color.GRAY)
        val snackTextView: TextView = snackbar.view.findViewById(com.google.android.material.R.id.snackbar_text)
        snackTextView.setTextColor(Color.WHITE)
        snackTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20.0f)
        val snackActionView: Button = snackbar.view.findViewById(com.google.android.material.R.id.snackbar_action)
        snackActionView.setBackgroundColor(Color.BLACK)
        snackActionView.setTextColor(Color.RED)
        return snackbar
    }
}