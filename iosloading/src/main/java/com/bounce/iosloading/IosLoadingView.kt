package com.bounce.iosloading

import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.view.animation.RotateAnimation
import android.widget.RelativeLayout

class IosLoadingView @JvmOverloads constructor(
    private val context: Context,
    private val attrs: AttributeSet? = null,
    private val defStyleAttr: Int = 0,
) :
    RelativeLayout(
        context,
        attrs,
        defStyleAttr
    ) {

    private var radius: Float
    private val numberOfBars = 12

    var arrBars: ArrayList<IosLoadingBarView>? = null

    private var isAnimating = false
    private var currentFrame = 0

    private val handler = Handler()
    private var playFrameRunnable: Runnable? = null

    init {
        val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.IosLoadingView, defStyleAttr, 0)
        radius = typedArray.getDimension(R.styleable.IosLoadingView_radius, 40f)
        typedArray.recycle()
        initViews()
        initLayouts()
        addViews()
        spreadBars()
        startAnimating()

    }


    fun initViews() {
        arrBars = ArrayList()

        for (i in 0 until numberOfBars) {
            val bar = IosLoadingBarView(
                context, radius / 10.0f
            )

            arrBars!!.add(bar)
        }
    }

    fun initLayouts() {
        for (i in 0 until numberOfBars) {
            val bar = arrBars!![i]

            bar.id = generateViewId()

            val barLayoutParams = LayoutParams(
                (radius / 5.0f).toInt(),
                (radius / 2.0f).toInt()
            )

            barLayoutParams.addRule(ALIGN_PARENT_TOP)
            barLayoutParams.addRule(CENTER_HORIZONTAL)

            bar.layoutParams = barLayoutParams
        }
    }

    fun addViews() {
        for (i in 0 until numberOfBars) {
            val bar = arrBars!![i]

            addView(bar)
        }
    }

    fun spreadBars() {
        var degrees = 0

        for (i in arrBars!!.indices) {
            val bar = arrBars!![i]

            rotateBar(bar, degrees.toFloat())

            degrees += 30
        }
    }

    private fun rotateBar(bar: IosLoadingBarView, degrees: Float) {
        val animation = RotateAnimation(0f, degrees, radius / 10.0f, radius)
        animation.duration = 0
        animation.fillAfter = true

        bar.animation = animation
        animation.start()
    }

    fun startAnimating() {
        alpha = 1.0f

        isAnimating = true

        playFrameRunnable = Runnable { playFrame() }

        // recursive function until isAnimating is false
        playFrame()
    }

    fun stopAnimating() {
        isAnimating = false

        alpha = 0.0f

        invalidate()

        playFrameRunnable = null
    }

    private fun playFrame() {
        if (isAnimating) {
            resetAllBarAlpha()
            updateFrame()
            handler.postDelayed(playFrameRunnable!!, 70)
        }
    }

    private fun updateFrame() {
        if (isAnimating) {
            showFrame(currentFrame)
            currentFrame += 1

            if (currentFrame > 11) {
                currentFrame = 0
            }
        }
    }

    private fun resetAllBarAlpha() {
        var bar: IosLoadingBarView? = null

        for (i in arrBars!!.indices) {
            bar = arrBars!![i]

            bar.alpha = 0.5f
        }
    }

    private fun showFrame(frameNumber: Int) {
        val indexes = getFrameIndexesForFrameNumber(frameNumber)

        gradientColorBarSets(indexes)
    }

    private fun getFrameIndexesForFrameNumber(frameNumber: Int): IntArray {
        return if (frameNumber == 0) {
            indexesFromNumbers(0, 11, 10, 9)
        } else if (frameNumber == 1) {
            indexesFromNumbers(1, 0, 11, 10)
        } else if (frameNumber == 2) {
            indexesFromNumbers(2, 1, 0, 11)
        } else if (frameNumber == 3) {
            indexesFromNumbers(3, 2, 1, 0)
        } else if (frameNumber == 4) {
            indexesFromNumbers(4, 3, 2, 1)
        } else if (frameNumber == 5) {
            indexesFromNumbers(5, 4, 3, 2)
        } else if (frameNumber == 6) {
            indexesFromNumbers(6, 5, 4, 3)
        } else if (frameNumber == 7) {
            indexesFromNumbers(7, 6, 5, 4)
        } else if (frameNumber == 8) {
            indexesFromNumbers(8, 7, 6, 5)
        } else if (frameNumber == 9) {
            indexesFromNumbers(9, 8, 7, 6)
        } else if (frameNumber == 10) {
            indexesFromNumbers(10, 9, 8, 7)
        } else {
            indexesFromNumbers(11, 10, 9, 8)
        }
    }

    private fun indexesFromNumbers(i1: Int, i2: Int, i3: Int, i4: Int): IntArray {
        val indexes = intArrayOf(i1, i2, i3, i4)
        return indexes
    }

    private fun gradientColorBarSets(indexes: IntArray) {
        var alpha = 1.0f

        var barView: IosLoadingBarView? = null

        for (i in indexes.indices) {
            val barIndex = indexes[i]

            barView = arrBars!![barIndex]


            barView.alpha = alpha
            alpha -= 0.125f
        }

        invalidate()
    }
}