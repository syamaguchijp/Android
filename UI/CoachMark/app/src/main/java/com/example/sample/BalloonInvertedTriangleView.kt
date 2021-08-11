package com.example.sample

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Point

// 逆三角形の矢印
class BalloonInvertedTriangleView(context: Context) : BalloonTriangleView(context) {

    override fun drawTriangle(canvas: Canvas) {

        paint.setColor(bgColor)
        paint.setStrokeWidth(1f)
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setAntiAlias(true);

        val a = Point(0, 0)
        val b = Point(50, 0)
        val c = Point(25, 50)

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