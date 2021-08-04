package com.example.sample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Activityの遷移。下から出るアニメーション付
        val button1 = findViewById<Button>(R.id.button)
        button1.setOnClickListener {
            val intent = Intent(application, SecondActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_from_bottom, R.anim.slide_from_top)
        }

        // BottomSheet
        val bottomSheet = findViewById<ConstraintLayout>(R.id.bottomSheet)
        var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                }
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    when (newState) {
                        BottomSheetBehavior.STATE_COLLAPSED -> println("STATE_COLLAPSED")
                        BottomSheetBehavior.STATE_EXPANDED -> println("STATE_EXPANDED")
                        BottomSheetBehavior.STATE_DRAGGING -> println("STATE_DRAGGING")
                        BottomSheetBehavior.STATE_SETTLING -> println("STATE_SETTLING")
                        BottomSheetBehavior.STATE_HIDDEN -> println("STATE_HIDDEN")
                        else -> println("STATE_OTHER")
                    }
                }
            })
        val button2 = findViewById<Button>(R.id.button2)
        button2.setOnClickListener {
            if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
                // 拡大
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            } else {
                // 最小化（peekHeight）
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        }

        // BottomSheet Modal（BottomSheetDialogFragment）
        val button3 = findViewById<Button>(R.id.button3)
        val bottomSheetFragment = MyBottomSheetDialogFragment()
        button3.setOnClickListener {
            bottomSheetFragment.show(supportFragmentManager, MyBottomSheetDialogFragment.TAG)
        }

    }
}