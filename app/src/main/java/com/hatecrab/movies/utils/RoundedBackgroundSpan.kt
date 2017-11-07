package com.hatecrab.movies.utils

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.text.style.ReplacementSpan

class RoundedBackgroundSpan(private val mBackgroundColor: Int, private val fullRoundedCorners: Boolean = false) : ReplacementSpan() {

    private val paddingY = if (fullRoundedCorners) FULL_ROUNDED_PADDING_Y else DEFAULT_PADDING_Y
    private val paddingX = if (fullRoundedCorners) FULL_ROUNDED_PADDING_X else DEFAULT_PADDING_X

    override fun getSize(paint: Paint, text: CharSequence, start: Int, end: Int, fm: Paint.FontMetricsInt?): Int {
        return (paddingX + paint.measureText(text.subSequence(start, end).toString()) + paddingX).toInt()
    }

    override fun draw(canvas: Canvas, text: CharSequence, start: Int, end: Int, x: Float, top: Int, y: Int, bottom: Int, paint: Paint) {
        val paint1 = Paint(paint)
        val width = paint1.measureText(text.subSequence(start, end).toString())
        val rect = RectF(x, top.toFloat() - paddingY, x + width + 2 * paddingX, bottom.toFloat() + paddingY)
        val cornersRadius = if (fullRoundedCorners) width / 2 else DEFAULT_CORNER_RADIUS
        paint1.color = mBackgroundColor
        canvas.drawRoundRect(rect, cornersRadius, cornersRadius, paint1)
        canvas.drawText(text, start, end, x + paddingX, y.toFloat(), paint)
    }

    companion object {
        private val DEFAULT_CORNER_RADIUS = 6F
        private val DEFAULT_PADDING_X = 10
        private val DEFAULT_PADDING_Y = 4
        private val FULL_ROUNDED_PADDING_X = 20
        private val FULL_ROUNDED_PADDING_Y = 8
    }
}