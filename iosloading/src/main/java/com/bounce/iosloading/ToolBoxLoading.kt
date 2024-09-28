package com.bounce.iosloading

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Paint
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape

class ToolBoxLoading
private constructor() {
    var context: Context? = null

    companion object {
        @SuppressLint("StaticFieldLeak")
        @get:Synchronized
        var instance: ToolBoxLoading? = null
            get() {
                if (field == null) {
                    field = ToolBoxLoading()
                }

                return field
            }
            private set

        fun roundedCornerRectOutlineWithColor(
            color: Int, cornerRadius: Float,
            strokeWidth: Float
        ): ShapeDrawable {
            val radii = floatArrayOf(
                cornerRadius, cornerRadius,
                cornerRadius, cornerRadius,
                cornerRadius, cornerRadius,
                cornerRadius, cornerRadius
            )

            val roundedCornerShape = RoundRectShape(radii, null, null)

            val shape = ShapeDrawable()
            shape.paint.color = color
            shape.shape = roundedCornerShape
            shape.paint.strokeWidth = strokeWidth
            shape.paint.style = Paint.Style.STROKE

            return shape
        }

        fun roundedCornerRectWithColor(color: Int, cornerRadius: Float): ShapeDrawable {
            val radii = floatArrayOf(
                cornerRadius, cornerRadius,
                cornerRadius, cornerRadius,
                cornerRadius, cornerRadius,
                cornerRadius, cornerRadius
            )

            val roundedCornerShape = RoundRectShape(radii, null, null)

            val shape = ShapeDrawable()
            shape.paint.color = color
            shape.shape = roundedCornerShape

            return shape
        }

        fun roundedCornerRectWithColor(
            color: Int,
            topLeftRadius: Float,
            topRightRadius: Float,
            bottomRightRadius: Float,
            bottomLeftRadius: Float
        ): ShapeDrawable {
            val radii = floatArrayOf(
                topLeftRadius, topLeftRadius,
                topRightRadius, topRightRadius,
                bottomRightRadius, bottomRightRadius,
                bottomLeftRadius, bottomLeftRadius
            )

            val roundedCornerShape = RoundRectShape(radii, null, null)

            val shape = ShapeDrawable()
            shape.paint.color = color
            shape.shape = roundedCornerShape

            return shape
        }

        val screenWidth: Int
            get() = Resources.getSystem().displayMetrics.widthPixels

        val screenHeight: Int
            get() = Resources.getSystem().displayMetrics.heightPixels

        fun getScreenOrientation(context: Context): Int {
            return context.resources.configuration.orientation
        }

        fun isLandscapeOrientation(context: Context): Boolean {
            return getScreenOrientation(context) == Configuration.ORIENTATION_LANDSCAPE
        }
    }
}