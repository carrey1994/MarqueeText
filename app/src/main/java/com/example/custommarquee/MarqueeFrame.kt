package com.example.custommarquee

import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.util.Log
import android.widget.*
import android.animation.ObjectAnimator
import android.graphics.Canvas
import androidx.core.animation.doOnEnd


class MarqueeFrame : HorizontalScrollView {
	constructor(context: Context) : super(context)
	constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
	constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
	
	private var mWidth = 0
	private val emptyGap = 600
	private var isDataDone = false
	private var isFirst = false
	private var mDuration = 2500L
	private val marqueeLayout = LinearLayout(context)
	private var isStart = false
	private var isViewParamFinish = false
	private lateinit var textViews: List<TextView>
	private var originalWidths = listOf<Int>()
	private val distanceList = arrayListOf<Int>()
	
	init {
		isVerticalScrollBarEnabled = false
		isHorizontalScrollBarEnabled = false
		isFocusable = false
		
	}
	
	fun setData(texts: List<String>) {
		val copyList = arrayListOf<TextView>().apply {
			addAll(texts.map {
				TextView(context).apply {
					text = it
				}
			})
			add(TextView(context).apply {
				text = texts[0]
			})
		}
		textViews = copyList
		textViews.forEach { marqueeLayout.addView(it) }
		addView(marqueeLayout)
		isDataDone = true
	}
	
	
	override fun onDraw(canvas: Canvas?) {
		super.onDraw(canvas)
		if (isDataDone.not()) return
		mWidth = width
		
		if (isViewParamFinish.not()) {
			originalWidths = textViews.map { it.width }
			textViews.forEach {
				if (it.width > mWidth)
					it.run { minWidth = this.paint.measureText(this.text.toString()).toInt() + emptyGap }
				else
					it.run { minWidth = mWidth }
				distanceList.add(it.minWidth)
			}
			isViewParamFinish = true
		}
		
		if (isStart.not()) {
			isStart = true
			for (i in textViews.indices) {
				var mDistance = 0
				for (k in 0 until i) {
					mDistance += distanceList[k]
				}
				
				when (i) {
					0 -> {
						if (originalWidths[0] > mWidth)
							animNoDelay(originalWidths[0] - mWidth, 0) {
								isStart = true
								isFirst = true
							}
//						else
//							animNoDelay(mWidth, 0) {
//								isStart = true
//								isFirst = true
//							}
					}
					textViews.lastIndex -> {
						val lastMove: Int = if (originalWidths[i] > mWidth)
							mDistance + originalWidths[i] - mWidth
						else
							mDistance //- mWidth - emptyGap
						
						val startTermination: Int = if (originalWidths[i] > mWidth)
							originalWidths[0] - mWidth
						else
							0 //- mWidth - emptyGap
						
						
						animOnEndReset(lastMove, i + 1) {
							scrollTo(startTermination, 0)
							isStart = false
						}
					}
					else -> {
						if (originalWidths[i] > mWidth) {
							//Too long
							animPostDelay(mDistance + originalWidths[i] - mWidth, i + 1) { isFirst = true }
						} else {
							//Normal
							animPostDelay(mDistance, i + 1) { isStart = true;isFirst = true }
						}
					}
				}
				
			}
		}
	}
	
	private fun getTextMeasureWidth(textView: TextView) = textView.paint.measureText(textView.text.toString()).toInt()
	
	private fun getOddDuration(times: Int) = mDuration * (2 * times - 1)
	
	private fun animPostDelay(distance: Int, durationTimes: Int, fn: (() -> Unit)? = null) {
		Handler().postDelayed({
			ObjectAnimator.ofInt(this, "scrollX", distance).apply {
				duration = mDuration
			}.start()
			fn?.invoke()
		}, getOddDuration(durationTimes))
	}
	
	private fun animNoDelay(distance: Int, durationTimes: Int, fn: (() -> Unit)? = null) {
		Handler().postDelayed({
			ObjectAnimator.ofInt(this, "scrollX", distance).apply {
				duration = mDuration
			}.start()
			fn?.invoke()
		}, 0)
		Log.e("xxxxx======>","foesfs")
	}
	
	private fun animOnEndReset(distance: Int, durationTimes: Int, fn: (() -> Unit)? = null) {
		Handler().postDelayed({
			ObjectAnimator.ofInt(this, "scrollX", distance).apply {
				duration = mDuration * 1.1.toLong()
				doOnEnd {
					fn?.invoke()
				}
			}.start()
		}, getOddDuration(durationTimes))
	}
	
}