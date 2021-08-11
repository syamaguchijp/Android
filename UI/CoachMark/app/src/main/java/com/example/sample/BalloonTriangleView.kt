package com.example.sample

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

// 矢印
open class BalloonTriangleView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    val paint = Paint()
    var bgColor: Int = Color.BLUE

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            drawTriangle(it)
        }
    }

    open fun drawTriangle(canvas: Canvas) {

        paint.setColor(bgColor)
        paint.setStrokeWidth(1f)
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setAntiAlias(true);

        val a = Point(25, 0)
        val b = Point(50, 50)
        val c = Point(0, 50)

        val path = Path()
        path.setFillType(Path.FillType.EVEN_ODD)
        path.moveTo(a.x.toFloat(), a.y.toFloat())
        path.lineTo(b.x.toFloat(), b.y.toFloat())
        path.lineTo(c.x.toFloat(), c.y.toFloat())
        path.lineTo(a.x.toFloat(), a.y.toFloat())

        path.close()
        canvas.drawPath(path, paint)
    }
}