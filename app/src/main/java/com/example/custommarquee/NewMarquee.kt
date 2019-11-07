package com.example.custommarquee

import android.content.Context
import android.graphics.Rect
import android.os.Handler
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.Scroller
import android.widget.TextView


class NewMarquee(context: Context, attrs: AttributeSet?, defStyle: Int) : TextView(context, attrs, defStyle) {
	
	// scrolling feature
	private var mSlr: Scroller? = null
	
	// milliseconds for a round of scrolling
	var rndDuration = 5000
	
	// the X offset when paused
	private var mXPaused = 0
	
	// whether it's being paused
	var isPaused = true
		private set
	val texts = listOf("嘟嘟嘟嚕嘟嘟嘟嚕嘟嘟嘟嚕嘟嘟嘟嚕嘟嘟嘟嚕嘟嘟嘟嚕答答答")
	var times = 0
	
	/*
     * constructor
     */
	constructor(context: Context) : this(context, null) {
		// customize the TextView
		setSingleLine()
		ellipsize = null
		visibility = View.INVISIBLE
	}
	
	/*
     * constructor
     */
	constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, android.R.attr.textViewStyle) {
		// customize the TextView
		setSingleLine()
		ellipsize = null
		visibility = View.INVISIBLE
	}
	
	init {
		// customize the TextView
		setSingleLine()
		ellipsize = null
		visibility = View.INVISIBLE
	}
	
	/**
	 * begin to scroll the text from the original position
	 */
	fun startScroll() {
		// begin from the very right side
		mXPaused = -1 * width
		// assume it's paused
		isPaused = true
		resumeScroll()
	}
	
	/**
	 * resume the scroll from the pausing point
	 */
	fun resumeScroll() {
		
		if (!isPaused) return
		
		// Do not know why it would not scroll sometimes
		// if setHorizontallyScrolling is called in constructor.
		setHorizontallyScrolling(true)
		
		// use LinearInterpolator for steady scrolling
		mSlr = Scroller(this.context, LinearInterpolator())
		setScroller(mSlr)
		
		val scrollingLen = calculateScrollingLen()
		val distance = scrollingLen - (width + mXPaused)
		val duration = (rndDuration.toDouble() * distance.toDouble() * 1.00000 / scrollingLen).toInt()
		
		visibility = View.VISIBLE
		mSlr!!.startScroll(mXPaused, 0, distance, 0, duration)
		invalidate()
		isPaused = false
		
		val handler = Handler()
		val runnable = object : Runnable {
			override fun run() {
//				isPaused = !isPaused
				times++
				Log.e("TheTimes===", "$times")
				text = texts[(0)]
//				isPaused = true
				handler.postDelayed(this, 5000)
			}
		}
		handler.post(runnable)
		
		
	}
	
	/**
	 * calculate the scrolling length of the text in pixel
	 *
	 * @return the scrolling length in pixels
	 */
	private fun calculateScrollingLen(): Int {
		val tp = paint
		var rect: Rect? = Rect()
		val strTxt = text.toString()
		tp.getTextBounds(strTxt, 0, strTxt.length, rect)
		val scrollingLen = rect!!.width() + width
		rect = null
		return scrollingLen
	}
	
	/**
	 * pause scrolling the text
	 */
	fun pauseScroll() {
		if (null == mSlr) return
		
		if (isPaused)
			return
		
		isPaused = true
		
		// abortAnimation sets the current X to be the final X,
		// and sets isFinished to be true
		// so current position shall be saved
		mXPaused = mSlr!!.currX
		
		mSlr!!.abortAnimation()
	}
	
	override/*
     * override the computeScroll to restart scrolling when finished so as that
     * the text is scrolled forever
     */ fun computeScroll() {
		super.computeScroll()
		
		if (null == mSlr) return
		
		if (mSlr!!.isFinished && !isPaused) {
			this.startScroll()
		}
	}
}