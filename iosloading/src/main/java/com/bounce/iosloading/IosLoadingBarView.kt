package com.bounce.iosloading

import android.content.Context
import android.graphics.Color
import android.widget.RelativeLayout

class IosLoadingBarView(private val context: Context, private val cornerRadius: Float) :
    RelativeLayout(context) {
    init {
        initViews()
    }

    fun initViews() {
//        setBackground(ToolBox.roundedCornerRectWithColor(
//                Color.argb(255, 255, 255, 255), cornerRadius));

        background = ToolBoxLoading.roundedCornerRectWithColor(
            Color.GRAY,
            cornerRadius
        )

        alpha = 0.5f
    }

    fun resetColor() {
        background = ToolBoxLoading.roundedCornerRectWithColor(
            Color.argb(255, 255, 255, 255), cornerRadius
        )

        alpha = 0.5f
    }
}