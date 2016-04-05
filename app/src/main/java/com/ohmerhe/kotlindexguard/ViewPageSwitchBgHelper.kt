package com.ohmerhe.kotlindexguard

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.TransitionDrawable
import android.os.Handler
import android.widget.ImageView

/**
 * Created by ohmer on 3/28/16.
 */

class ViewPageSwitchBgHelper(val imageView: ImageView?) {
    companion object {
        val duration = 1000
        val delay = 500L
        val LAYER_FIRST = 0
        val LAYER_SECOND = 1
    }

    lateinit var transitionDrawable: TransitionDrawable
    private var backDrawableLayerId = LAYER_SECOND

    init {
        initTransitionDrawable()
    }

    fun initTransitionDrawable() {
        val drawable = imageView?.drawable ?: ColorDrawable(Color.LTGRAY)
        transitionDrawable = TransitionDrawable(arrayOf(drawable, ColorDrawable()))
        imageView?.setImageDrawable(transitionDrawable)
        transitionDrawable.setId(0, LAYER_FIRST)
        transitionDrawable.setId(1, LAYER_SECOND)
    }

    private val weakHandler: Handler = Handler()
    private var isPrepared = false
    private var excuteRunnable: Runnable? = null

    fun swtichBgImageAnim(loadedImage: Drawable) {
        if (isPrepared) {
            weakHandler.removeCallbacks(excuteRunnable)
        }
        isPrepared = true
        excuteRunnable = Runnable { excuteSwtichBgImageAnim(loadedImage) }

        weakHandler.postDelayed(excuteRunnable, delay)
    }

    private fun excuteSwtichBgImageAnim(loadedImage: Drawable) {
        isPrepared = false
        val drawable = imageView?.drawable ?: ColorDrawable(Color.LTGRAY)
        if (drawable is TransitionDrawable) {
            transitionDrawable = drawable
            transitionDrawable.setDrawableByLayerId(backDrawableLayerId, loadedImage)
            backDrawableLayerId = select(backDrawableLayerId == LAYER_FIRST, LAYER_SECOND, LAYER_FIRST)
            if (backDrawableLayerId == LAYER_FIRST) {
                transitionDrawable.startTransition(duration)
            } else {
                transitionDrawable.reverseTransition(duration)
            }
        } else {
            initTransitionDrawable()
            excuteSwtichBgImageAnim(loadedImage)
        }

    }

    fun <T> select(isTrue: Boolean, param1: T, param2: T) = if (isTrue) param1 else param2

}