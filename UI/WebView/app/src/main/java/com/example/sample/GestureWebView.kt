package com.example.sample

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.webkit.WebView
import androidx.appcompat.widget.Toolbar

/*
class GestureWebView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : WebView(context, attrs, defStyleAttr) {
*/
class GestureWebView: WebView {

    constructor(context: Context) : super(context, null) {}
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    //region Flick

    private val X_MIN_DISTANCE = 100
    private val X_MIN_VELOCITY = 200
    private val Y_MAX = 200

    private var gestureDetector: GestureDetector? = null

    private val gestureListener = object : GestureDetector.SimpleOnGestureListener() {
        // フリックを検知
        override fun onFling(event1: MotionEvent, event2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
            try {
                val dX = event1.x - event2.x
                val vX = Math.abs(velocityX)
                val dY = Math.abs(event1.y - event2.y)
                Log.d("onFling", "x: $dX $vX  y: $dY" )

                if (dY > Y_MAX) {
                    Log.d("onFling","over Y, so return")
                } else if (dX > X_MIN_DISTANCE && vX > X_MIN_VELOCITY) {
                    Log.d("onFling","moveLeft")
                    moveLeft()
                } else if (Math.abs(dX) > X_MIN_DISTANCE && vX > X_MIN_VELOCITY) {
                    Log.d("onFling","moveRight")
                    moveRight()
                }

            } catch (e: Exception) {
                Log.e("onFling", e.toString())
            }

            return super.onFling(event1, event2, velocityX, velocityY)
        }

        private fun moveLeft() {
            goForward()
        }

        private fun moveRight() {
            goBack()
        }
    }

    init {
        gestureDetector = GestureDetector(context, gestureListener)

        // 以下を記述しないとonFlingはコールされない
        this.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                gestureDetector!!.onTouchEvent(event)
                return false
            }
        })
    }

    //endregion

    //region Scroll

    var toolbar: Toolbar? = null

    override fun onScrollChanged(scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int) {
        super.onScrollChanged(scrollX, scrollY, oldScrollX, oldScrollY)
        Log.i("onScrollChanged", "${scrollY} ${oldScrollY}")
        if (scrollY > oldScrollY) {
            toolbar?.let {
                // 下を見ようとしているため、画面下部のツールバーを非表示にする
                it.setVisibility(INVISIBLE)
            }
        } else if (scrollY < oldScrollY) {
            toolbar?.let {
                it.setVisibility(VISIBLE)
            }
        }
    }

    //endregion
}