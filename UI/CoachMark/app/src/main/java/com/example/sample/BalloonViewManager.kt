package com.example.sample

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet

class BalloonViewManager {

    fun startCoachMarkBottom(v: AppCompatActivity, context: Context, message: String, targetView: View) {

        val constraintLayout = v.findViewById<ConstraintLayout>(R.id.constraint_layout)
        val balloonTriangleView = generateBalloonTriangleView(context)
        constraintLayout.addView(balloonTriangleView)
        val balloonView = generateBalloonView(context)
        constraintLayout.addView(balloonView)
        val balloonTextView = generateBalloonTextView(message, context)
        constraintLayout.addView(balloonTextView)

        // BalloonTriangleViewのConstraint
        val constraintSet = ConstraintSet()
        constraintSet.clone(constraintLayout)
        constraintSet.connect( // TOP
            balloonTriangleView.id,
            ConstraintSet.TOP,
            targetView.id,
            ConstraintSet.BOTTOM,
            0
        )
        constraintSet.connect( // START
            balloonTriangleView.id,
            ConstraintSet.START,
            targetView.id,
            ConstraintSet.START,
            0
        )
        constraintSet.constrainWidth(balloonTriangleView.id, 50)
        constraintSet.constrainHeight(balloonTriangleView.id, 50)
        constraintSet.applyTo(constraintLayout)

        // balloonViewのConstraint
        val constraintSet2 = ConstraintSet()
        constraintSet2.clone(constraintLayout)
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
        constraintSet2.constrainWidth(balloonView.id, 0)
        constraintSet2.constrainPercentWidth(balloonView.id, 0.5f)
        constraintSet2.connect( // START
            balloonView.id,
            ConstraintSet.START,
            ConstraintSet.PARENT_ID,
            ConstraintSet.START,
            20
        )
        /*
        constraintSet2.connect( // END
            balloonView.id,
            ConstraintSet.END,
            ConstraintSet.PARENT_ID,
            ConstraintSet.END,
            20
        )*/
        constraintSet2.applyTo(constraintLayout)

        // BalloonTextViewのConstraint
        val constraintSet3 = ConstraintSet()
        constraintSet3.clone(constraintLayout)
        constraintSet3.connect( // TOP
            balloonTextView.id,
            ConstraintSet.TOP,
            balloonView.id,
            ConstraintSet.TOP,
            0
        )
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

    private fun generateBalloonTriangleView(context: Context): BalloonTriangleView {

        val triangleView = BalloonTriangleView(context)
        triangleView.id = View.generateViewId()
        return triangleView
    }

    private fun generateBalloonView(context: Context): View {

        val customView = View(context)
        customView.id = View.generateViewId()
        var params = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT, 0
        )
        customView.layoutParams = params

        // 角丸
        val shape = GradientDrawable()
        shape.setShape(GradientDrawable.RECTANGLE)
        shape.setColor(Color.GRAY)
        shape.setCornerRadius(15f)
        customView.setBackground(shape)

        return customView
    }

    private fun generateBalloonTextView(message: String, context: Context): TextView {

        val textView = TextView(context)
        textView.id = View.generateViewId()
        textView.text = message
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