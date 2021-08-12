package com.example.sample

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet

enum class BalloonViewVertical {
    TOP, BOTTOM
}
enum class BalloonViewHorizontal {
    LEFT, CENTER, RIGHT,
}

class BalloonViewManager {

    fun startCoachMarkBottomLeft(v: View, context: Context, constraintLayout: ConstraintLayout,
                             message: String, targetView: View, widthPersent: Float, bgColor: Int) {
        startCoachMark(v, context, constraintLayout, message, targetView,
            BalloonViewVertical.BOTTOM, BalloonViewHorizontal.LEFT, widthPersent, bgColor)
    }

    fun startCoachMarkBottomCenter(v: View, context: Context, constraintLayout: ConstraintLayout,
                                 message: String, targetView: View, widthPersent: Float, bgColor: Int) {
        startCoachMark(v, context, constraintLayout, message, targetView,
            BalloonViewVertical.BOTTOM, BalloonViewHorizontal.CENTER, widthPersent, bgColor)
    }

    fun startCoachMarkBottomRight(v: View, context: Context, constraintLayout: ConstraintLayout,
                                 message: String, targetView: View, widthPersent: Float, bgColor: Int) {
        startCoachMark(v, context, constraintLayout, message, targetView,
            BalloonViewVertical.BOTTOM, BalloonViewHorizontal.RIGHT, widthPersent, bgColor)
    }

    fun startCoachMarkTopLeft(v: View, context: Context, constraintLayout: ConstraintLayout,
                                 message: String, targetView: View, widthPersent: Float, bgColor: Int) {
        startCoachMark(v, context, constraintLayout, message, targetView,
            BalloonViewVertical.TOP, BalloonViewHorizontal.LEFT, widthPersent, bgColor)
    }

    fun startCoachMarkTopCenter(v: View, context: Context, constraintLayout: ConstraintLayout,
                              message: String, targetView: View, widthPersent: Float, bgColor: Int) {
        startCoachMark(v, context, constraintLayout, message, targetView,
            BalloonViewVertical.TOP, BalloonViewHorizontal.CENTER, widthPersent, bgColor)
    }

    fun startCoachMarkTopRight(v: View, context: Context, constraintLayout: ConstraintLayout,
                              message: String, targetView: View, widthPersent: Float, bgColor: Int) {
        startCoachMark(v, context, constraintLayout, message, targetView,
            BalloonViewVertical.TOP, BalloonViewHorizontal.RIGHT, widthPersent, bgColor)
    }

    private fun startCoachMark(v: View, context: Context, constraintLayout: ConstraintLayout,
                             message: String, targetView: View, vPosition: BalloonViewVertical,
                             hPosition: BalloonViewHorizontal, widthPersent: Float, bgColor: Int) {

        // コーチマークを形成するViewを生成
        val balloonTriangleView = generateBalloonTriangleView(context, bgColor, vPosition)
        constraintLayout.addView(balloonTriangleView)
        val balloonView = generateBalloonView(context, bgColor)
        constraintLayout.addView(balloonView)
        val balloonTextView = generateBalloonTextView(message, context)
        constraintLayout.addView(balloonTextView)

        // タップされたらコーチマークを消す
        balloonView.setOnTouchListener { v, event ->
            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    constraintLayout.removeView(balloonTriangleView)
                    constraintLayout.removeView(balloonView)
                    constraintLayout.removeView(balloonTextView)
                }
            }
            true
        }

        // Constraintの設定

        // BalloonTriangleViewのConstraint
        val constraintSet = ConstraintSet()
        constraintSet.clone(constraintLayout)
        if (vPosition == BalloonViewVertical.BOTTOM) {
            constraintSet.connect( // TOP
                balloonTriangleView.id,
                ConstraintSet.TOP,
                targetView.id,
                ConstraintSet.BOTTOM,
                0
            )
        } else if (vPosition == BalloonViewVertical.TOP) {
            constraintSet.connect( // BOTTOM
                balloonTriangleView.id,
                ConstraintSet.BOTTOM,
                targetView.id,
                ConstraintSet.TOP,
                0
            )
        }
        constraintSet.connect( // START
            balloonTriangleView.id,
            ConstraintSet.START,
            targetView.id,
            ConstraintSet.START,
            0
        )
        constraintSet.connect( // END
            balloonTriangleView.id,
            ConstraintSet.END,
            targetView.id,
            ConstraintSet.END,
            0
        )
        constraintSet.constrainWidth(balloonTriangleView.id, 50)
        constraintSet.constrainHeight(balloonTriangleView.id, 50)
        constraintSet.applyTo(constraintLayout)

        // balloonViewのConstraint
        val constraintSet2 = ConstraintSet()
        constraintSet2.clone(constraintLayout)
        if (vPosition == BalloonViewVertical.BOTTOM) {
            constraintSet2.connect( // TOP
                balloonView.id,
                ConstraintSet.TOP,
                balloonTriangleView.id,
                ConstraintSet.BOTTOM,
                0
            )
            constraintSet2.connect( // BOTTOM
                balloonView.id,
                ConstraintSet.BOTTOM,
                balloonTextView.id,
                ConstraintSet.BOTTOM,
                0
            )
        } else if (vPosition == BalloonViewVertical.TOP) {
            constraintSet2.connect( // TOP
                balloonView.id,
                ConstraintSet.TOP,
                balloonTextView.id,
                ConstraintSet.TOP,
                0
            )
            constraintSet2.connect( // BOTTOM
                balloonView.id,
                ConstraintSet.BOTTOM,
                balloonTriangleView.id,
                ConstraintSet.TOP,
                0
            )
        }
        constraintSet2.constrainWidth(balloonView.id, 0)
        constraintSet2.constrainPercentWidth(balloonView.id, widthPersent)
        if (hPosition == BalloonViewHorizontal.LEFT || hPosition == BalloonViewHorizontal.CENTER) {
            constraintSet2.connect( // START
                balloonView.id,
                ConstraintSet.START,
                ConstraintSet.PARENT_ID,
                ConstraintSet.START,
                30
            )
        }
        if (hPosition == BalloonViewHorizontal.RIGHT || hPosition == BalloonViewHorizontal.CENTER) {
            constraintSet2.connect( // END
                balloonView.id,
                ConstraintSet.END,
                ConstraintSet.PARENT_ID,
                ConstraintSet.END,
                30
            )
        }
        constraintSet2.applyTo(constraintLayout)

        // BalloonTextViewのConstraint
        val constraintSet3 = ConstraintSet()
        constraintSet3.clone(constraintLayout)
        if (vPosition == BalloonViewVertical.BOTTOM) {
            constraintSet3.connect( // TOP
                balloonTextView.id,
                ConstraintSet.TOP,
                balloonView.id,
                ConstraintSet.TOP,
                0
            )
        }
        constraintSet3.connect( // BOTTOM
            balloonTextView.id,
            ConstraintSet.BOTTOM,
            balloonView.id,
            ConstraintSet.BOTTOM,
            0
        )
        constraintSet3.connect( // START
            balloonTextView.id,
            ConstraintSet.START,
            balloonView.id,
            ConstraintSet.START,
            0
        )
        constraintSet3.connect( // END
            balloonTextView.id,
            ConstraintSet.END,
            balloonView.id,
            ConstraintSet.END,
            0
        )
        constraintSet3.applyTo(constraintLayout)

        balloonTriangleView.bringToFront()
        balloonView.bringToFront()
        balloonTextView.bringToFront()
    }

    // 矢印部分
    private fun generateBalloonTriangleView(context: Context, bgColor: Int, vPosition: BalloonViewVertical): BalloonTriangleView {

        if (vPosition == BalloonViewVertical.BOTTOM) {
            val triangleView = BalloonTriangleView(context)
            triangleView.bgColor = bgColor
            triangleView.id = View.generateViewId()
            return triangleView
        }

        val triangleView = BalloonInvertedTriangleView(context)
        triangleView.bgColor = bgColor
        triangleView.id = View.generateViewId()
        return triangleView
    }

    // 長方形部分
    private fun generateBalloonView(context: Context, bgColor: Int): View {

        val customView = View(context)
        customView.id = View.generateViewId()
        var params = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT, 0
        )
        customView.layoutParams = params
        // 角丸
        val shape = GradientDrawable()
        shape.setShape(GradientDrawable.RECTANGLE)
        shape.setColor(bgColor)
        shape.setCornerRadius(15f)
        customView.setBackground(shape)
        return customView
    }

    // 長方形内のTextView
    private fun generateBalloonTextView(message: String, context: Context): TextView {

        val textView = TextView(context)
        textView.id = View.generateViewId()
        textView.text = message
        textView.setTextColor(Color.WHITE)
        textView.textSize = 16f
        textView.setGravity(Gravity.CENTER_VERTICAL)
        val pad = 25
        textView.setPadding(pad, pad, pad, pad)
        val params = ConstraintLayout.LayoutParams(
            0, ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        textView.layoutParams = params
        return textView
    }
}