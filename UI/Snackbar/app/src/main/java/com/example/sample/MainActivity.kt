package com.example.sample

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams
import androidx.constraintlayout.widget.ConstraintSet
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintLayout.TEXT_ALIGNMENT_CENTER
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private val minContentsHeight = 300

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button1 = findViewById<Button>(R.id.button)
        button1.stateListAnimator = null // こうしないとConstraintLayoutにおいてbuttonが常に最前面に配置される
        button1.setOnClickListener {
            showSnackBar()
        }

        val button2 = findViewById<Button>(R.id.button2)
        button2.stateListAnimator = null
        button2.setOnClickListener {
            showSnackBarFromTop()
        }

        val button3 = findViewById<Button>(R.id.button3)
        button3.stateListAnimator = null
        button3.setOnClickListener {
            val message = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
            showCustomViewBottom(message)
        }

        val button4 = findViewById<Button>(R.id.button4)
        button4.stateListAnimator = null
        button4.setOnClickListener {
            val message = "Lorem ipsum dolor sit amet"
            showCustomViewTop(message)
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

        val message = "吾輩わがはいは猫である。名前はまだ無い。どこで生れたかとんと見当けんとうがつかぬ。何でも薄暗いじめじめした所でニャーニャー泣いていた事だけは記憶している。吾輩はここで始めて人間というものを見た。"
        val snackbar = Snackbar.make(rootLayout, message, Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction("CLOSE") {
            snackbar.dismiss()
        }
        snackbar.view.setBackgroundColor(Color.GRAY)

        // テキスト
        val snackTextView: TextView = snackbar.view.findViewById(com.google.android.material.R.id.snackbar_text)
        snackTextView.setTextColor(Color.WHITE)
        snackTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18.0f)
        snackTextView.setMaxLines(20)
        //snackTextView.setLines(10)
        //snackTextView.setHeight(450)

        // ボタン
        val snackActionView: Button = snackbar.view.findViewById(com.google.android.material.R.id.snackbar_action)
        snackActionView.setBackgroundColor(Color.BLACK)
        snackActionView.setTextColor(Color.WHITE)
        snackActionView.setHeight(50)

        return snackbar
    }

    private fun showCustomViewBottom(message: String) {

        val constraintLayout = findViewById<ConstraintLayout>(R.id.constraint_layout)
        val customView = generateCustomView()
        constraintLayout.addView(customView)
        val textView = generateTextView(message)
        constraintLayout.addView(textView)

        // TextViewのConstraint
        val constraintSet2 = ConstraintSet()
        constraintSet2.clone(constraintLayout)
        constraintSet2.connect( // TOPの設定
            textView.id,
            ConstraintSet.TOP,
            ConstraintSet.PARENT_ID,//R.id.constraint_layout,
            ConstraintSet.BOTTOM,
            0
        )
        constraintSet2.connect( // STARTの設定
            textView.id,
            ConstraintSet.START,
            customView.id,
            ConstraintSet.START,
            0
        )
        constraintSet2.connect( // ENDの設定
            textView.id,
            ConstraintSet.END,
            customView.id,
            ConstraintSet.END,
            0
        )
        constraintSet2.applyTo(constraintLayout)

        // ViewのConstraint
        val constraintSet = ConstraintSet()
        constraintSet.clone(constraintLayout)
        constraintSet.connect( // TOPの設定
            customView.id,
            ConstraintSet.TOP,
            ConstraintSet.PARENT_ID,//R.id.constraint_layout,
            ConstraintSet.BOTTOM,
            0
        )
        constraintSet.connect( // BOTTOMの設定
            customView.id,
            ConstraintSet.BOTTOM,
            textView.id,
            ConstraintSet.BOTTOM,
            0
        )
        constraintSet.applyTo(constraintLayout)

        customView.bringToFront()
        textView.bringToFront()

        modLayout(customView, textView, false)
    }

    private fun showCustomViewTop(message: String) {

        val constraintLayout = findViewById<ConstraintLayout>(R.id.constraint_layout)
        val customView = generateCustomView()
        constraintLayout.addView(customView)
        val textView = generateTextView(message)
        constraintLayout.addView(textView)

        // TextViewのConstraint
        val constraintSet2 = ConstraintSet()
        constraintSet2.clone(constraintLayout)
        constraintSet2.connect( // BOTTOMの設定
            textView.id,
            ConstraintSet.BOTTOM,
            ConstraintSet.PARENT_ID,//R.id.constraint_layout,
            ConstraintSet.TOP,
            0
        )
        constraintSet2.connect( // STARTの設定
            textView.id,
            ConstraintSet.START,
            customView.id,
            ConstraintSet.START,
            0
        )
        constraintSet2.connect( // ENDの設定
            textView.id,
            ConstraintSet.END,
            customView.id,
            ConstraintSet.END,
            0
        )
        constraintSet2.applyTo(constraintLayout)

        // ViewのConstraint
        val constraintSet = ConstraintSet()
        constraintSet.clone(constraintLayout)
        constraintSet.connect( // BOTTOMの設定
            customView.id,
            ConstraintSet.BOTTOM,
            ConstraintSet.PARENT_ID,//R.id.constraint_layout,
            ConstraintSet.TOP,
            0
        )
        constraintSet.connect( // TOPの設定
            customView.id,
            ConstraintSet.TOP,
            textView.id,
            ConstraintSet.TOP,
            0
        )
        constraintSet.applyTo(constraintLayout)

        customView.bringToFront()
        textView.bringToFront()

        modLayout(customView, textView, true)
    }

    private fun generateTextView(message: String): TextView {

        val textView = TextView(this)
        textView.id = View.generateViewId()
        textView.text = message
        textView.setGravity(Gravity.CENTER_VERTICAL)
        val pad = 10
        textView.setPadding(pad, pad, pad, pad)
        val params = LayoutParams(
            0, LayoutParams.WRAP_CONTENT
        )
        textView.layoutParams = params
        return textView
    }

    private fun generateCustomView(): View {

        val customView = View(this)
        customView.id = View.generateViewId()
        var params = LayoutParams(
            LayoutParams.MATCH_PARENT, 0
        )
        params.setMargins(0,0,0,0)
        customView.layoutParams = params
        customView.setBackgroundColor(Color.GRAY)
        return customView
    }

    private fun modLayout(customView: View, textView: TextView, isTop: Boolean) {

        textView.viewTreeObserver.addOnGlobalLayoutListener {
            // レイアウトが変更されるたびにコールされる
            println("##### textView.height = ${textView.height}")
            // TextViewの高さが小さいときは補正
            if (textView.height < minContentsHeight) {
                textView.height = minContentsHeight + 1
            }
        }

        var finishedAnimation = false
        customView.viewTreeObserver.addOnGlobalLayoutListener {
            // レイアウトが変更されるたびにコールされる
            println("##### customView.height = ${customView.height}")
            if (textView.height >= minContentsHeight) { // TextViewの高さ補正後にアニメーションを開始する
                if (!finishedAnimation) {
                    finishedAnimation = true
                    startAnimation(customView, textView, isTop)
                }
            }
        }
    }

    private fun startAnimation(customView: View, textView: TextView, isTop: Boolean) {

        println("startAnimation customView.height=${customView.height}")
        var multiple = 1
        if (!isTop) {
            multiple = -1
        }
        val animator = ObjectAnimator.ofFloat(customView, "translationY",
            multiple * customView.height.toFloat()).setDuration(1000)
        animator.start()
        val animator2 = ObjectAnimator.ofFloat(textView, "translationY",
            multiple * textView.height.toFloat()).setDuration(1000)
        animator2.start()
        val animatorList: MutableList<Animator> = ArrayList()
        animatorList.add(animator)
        animatorList.add(animator2)
        val animatorSet = AnimatorSet()
        animatorSet.playTogether(animatorList)
        animatorSet.start()

        customView.setOnClickListener {
            // タップされたときに、表示時とは逆のアニメーションを行って閉じる
            startAnimation(customView, textView, !isTop)
        }
    }

}