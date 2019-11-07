package com.example.custommarquee

import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.util.Log
import android.view.animation.LinearInterpolator
import android.widget.*
import android.animation.ObjectAnimator
import android.graphics.Canvas
import android.graphics.Color
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.animation.doOnEnd
import java.util.*


class MarqueeFrame : HorizontalScrollView {
	constructor(context: Context) : super(context)
	constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
	constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
	
	var mWidth = 0
	
	val emptyGap = 400
	
	
	val rnd = Random()
	//	val textViews = mTexts.map {
//		TextView(context).apply {
//			text = it
////			setBackgroundColor(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)))
//		}
//	}
	private lateinit var textViews: List<TextView>
	
	val marqueeLayout = LinearLayout(context)
	
	
	var isStart = false
	var isViewParamFinish = false
	
	init {
		isVerticalScrollBarEnabled = false
		isHorizontalScrollBarEnabled = false
		isFocusable = false
	}
	
	fun setData(texts: List<String>) {
		textViews = texts.map {
			TextView(context).apply {
				text = it
			}
		}
		textViews.forEach { marqueeLayout.addView(it) }
		addView(marqueeLayout)
		isRun = true
	}
	
	
	var isRun = false
	
	var delaytime = 1500L
	var mDuration = 3000L
	
	override fun onDraw(canvas: Canvas?) {
		super.onDraw(canvas)
		if (isRun.not()) return
		
		mWidth = width
		Log.e("mWidth===", "$mWidth")
		Log.e("HorizontalWidth=>>>>>", "${width}===")
		
		if (isViewParamFinish.not()) {
			textViews.forEach {
				if (it.width > mWidth)
					it.run { minWidth = this.paint.measureText(this.text.toString()).toInt() + emptyGap }
				else
					it.run { minWidth = mWidth }
				Log.e("TextViewWidthMin===>", "${it.width}")
			}
			
			
			isViewParamFinish = true
		}
		Log.e("First===", "${textViews[0].width}")
		Log.e("Second===", "${textViews[1].width}")
		//Solution2  but always from start
		if (isStart.not()) {
//			for (i in textViews.indices) {
//				var distance = 0
//				for (k in 0..i) {
//					distance += textViews[k].width
//				}
//				if (textViews[i].width > mWidth) {//Too long
//					Handler().postDelayed({
//						ObjectAnimator.ofInt(this, "scrollX", distance - mWidth - emptyGap).apply {
//							duration = mDuration
//
//							if (i == textViews.lastIndex)
//								doOnEnd {
//									scrollTo(0, 0)
//									isStart = false
//								}
//							if (i == 0) {
//								isStart = true
//							}
//						}.start()
//					}, getOddDuration(i + 1))
//				} else {
//					Handler().postDelayed({
//						ObjectAnimator.ofInt(this, "scrollX", distance).apply {
//							duration = mDuration
//
//							if (i == textViews.lastIndex)
//								doOnEnd {
//									scrollTo(0, 0)
//									isStart = false
//								}
//							if (i == 0) {
//								isStart = true
//							}
//						}.start()
//					}, getOddDuration(i + 1))
//				}
//			}
			
			Handler().postDelayed({
				ObjectAnimator.ofInt(this, "scrollX", textViews[0].width - mWidth - emptyGap).apply {
					duration = mDuration
				}.start()
				isStart = true
			}, getOddDuration(0))
			
			Handler().postDelayed({
				ObjectAnimator.ofInt(this, "scrollX", textViews[0].width).apply {
					duration = mDuration
				}.start()
				isStart = true
			}, getOddDuration(1))
//
			Handler().postDelayed({
				ObjectAnimator.ofInt(this, "scrollX", textViews[0].width + textViews[1].width).apply {
					duration = mDuration
				}.start()
			}, getOddDuration(2))
//
			Handler().postDelayed({
				ObjectAnimator.ofInt(this, "scrollX", textViews[0].width + textViews[1].width + textViews[2].width + textViews[3].width - mWidth - emptyGap).apply {
					duration = mDuration * 1.1.toLong()
					doOnEnd {
						scrollTo(textViews[0].paint.measureText(textViews[0].text.toString()).toInt() - mWidth, 0)
//						isStart = false
					}
				}.start()
			}, getOddDuration(3))
			
			
			Handler().postDelayed({
				ObjectAnimator.ofInt(this, "scrollX", textViews[0].width).apply {
					duration = mDuration
				}.start()
			}, getOddDuration(4))


//			Handler().postDelayed({
//				ObjectAnimator.ofInt(this, "scrollX", textViews[0].width - mWidth - emptyGap).apply {
//					duration = mDuration
//				}.start()
//				isStart = true
//			}, getOddDuration(0))
			
		}
		
		
	}
	
	fun getOddDuration(times: Int) = mDuration * (2 * times - 1)
	
	//region #test code
	//Solution1  but too fast
//		Handler().postDelayed({
//			smoothScrollBy(430, 0)
//		}, 1500)
//
//		Handler().postDelayed({
//			smoothScrollBy(1250, 0)
//		}, 3000)
	
	
	//Solution2  but always from start
//		Handler().postDelayed({
//			ObjectAnimator.ofInt(this, "scrollX", 220 + 250).apply {
//				duration = 1400
//			}.start()
//		}, 1500)
//
//		Handler().postDelayed({
//			ObjectAnimator.ofInt(this, "scrollX", 1680).apply {
//				duration = 1500
//			}.start()
//		}, 3000)


//		Handler().postDelayed({
//			smoothScrollBy(200, 0)
//		}, 4500)
	//endregion
	
	/**
	 *
	Handler().postDelayed({
	ObjectAnimator.ofInt(this, "scrollX", textViews[0].width - mWidth - emptyGap).apply {
	duration = mDuration
	}.start()
	isStart = true
	}, getOddDuration(0))
	
	Handler().postDelayed({
	ObjectAnimator.ofInt(this, "scrollX", textViews[0].width).apply {
	duration = mDuration
	}.start()
	isStart = true
	}, getOddDuration(1))
	//
	Handler().postDelayed({
	ObjectAnimator.ofInt(this, "scrollX", textViews[0].width + textViews[1].width).apply {
	duration = mDuration
	}.start()
	}, getOddDuration(2))
	//
	Handler().postDelayed({
	ObjectAnimator.ofInt(this, "scrollX", textViews[0].width + textViews[1].width + textViews[2].width).apply {
	duration = mDuration * 1.1.toLong()
	doOnEnd {
	scrollTo(0, 0)
	//						isStart = false
	}
	}.start()
	}, getOddDuration(3))
	 
	 */
	
	
}