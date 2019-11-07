//package com.example.custommarquee
//
//import android.content.Context
//import android.os.Handler
//import android.util.AttributeSet
//import android.util.Log
//import android.view.animation.LinearInterpolator
//import android.widget.*
//import android.animation.ObjectAnimator
//import android.graphics.Canvas
//
//
//class MarqueeFrame : HorizontalScrollView {
//	constructor(context: Context) : super(context)
//	constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
//	constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
//
//	val mScroller = Scroller(context, LinearInterpolator())
//	var mWidth = 0
//
//	val mTexts = listOf("嘟嘟嘟嚕嘟嘟嘟嚕嘟嘟嘟嚕嘟嘟嘟嚕嘟嘟嘟嚕嘟嘟嘟嚕答答嘟嚕答答", "AAAAAAAAAA!!!")
//
//
//	val longText = TextView(context).apply { text = "嘟嘟嘟嚕嘟嘟嘟嚕嘟嘟嘟嚕嘟嘟嘟嚕嘟嘟嘟嚕嘟嘟嘟嚕答答嘟嚕答答!!!" }
//	val emptyView = TextView(context).apply { minWidth = 400 }
//
//	val longText2 = TextView(context).apply { text = "2222222222222222222222222222!!!" }
//
//	val shortText = TextView(context).apply {
//		text = "AAAAAAAAAA!!!"
//		minWidth = 1500
//	}
//
//	init {
//		isVerticalScrollBarEnabled = false
//		isHorizontalScrollBarEnabled = false
//
//		val emptyView2 = TextView(context)
//
////		addView(view1)
//		val marqueeLayout = LinearLayout(context)
//		marqueeLayout.addView(longText)
//		marqueeLayout.addView(emptyView)
//		marqueeLayout.addView(shortText)
////		marqueeLayout.addView(emptyView2)
//
//
//		addView(marqueeLayout)
//		mScroller.startScroll(0, 0, 470, 0, 300)
//
//
//		val animator = ObjectAnimator.ofInt(this, "scrollX", 220 + 250)
//		animator.duration = 1400
//
////		animator.start()
//
////		val gap = (paint.measureText(text.toString()).toInt() - measuredWidth) * 2
//		Log.e("llWidth===>", "${longText.width}||${emptyView.width}||${shortText.width}")
//		Log.e("View1TextWidth=>>>>>", "${longText.paint.measureText(longText.text.toString()).toInt()}")
//		Log.e("HorizontalWidth=>>>>>", "${width}===")
////		Log.e("ParentWidth=>>>>>", "${(this.parent  as FrameLayout).width}")
//
//		isHorizontalFadingEdgeEnabled = true
//
//
//		//Solution1  but too fast
////		Handler().postDelayed({
////			smoothScrollBy(430, 0)
////		}, 1500)
////
////		Handler().postDelayed({
////			smoothScrollBy(1250, 0)
////		}, 3000)
//
//
//		//Solution2  but always from start
////		Handler().postDelayed({
////			ObjectAnimator.ofInt(this, "scrollX", 220 + 250).apply {
////				duration = 1400
////			}.start()
////		}, 1500)
////
////		Handler().postDelayed({
////			ObjectAnimator.ofInt(this, "scrollX", 1680).apply {
////				duration = 1500
////			}.start()
////		}, 3000)
//
//
////		Handler().postDelayed({
////			smoothScrollBy(200, 0)
////		}, 4500)
//		Log.e("mWidth2===", "$mWidth")
//	}
//
//	var isStart = false
//	override fun onDraw(canvas: Canvas?) {
//		super.onDraw(canvas)
//		mWidth = width
//		Log.e("mWidth===", "$mWidth")
//
//		Log.e("llWidth===>", "${longText.width}||${emptyView.width}||${shortText.width}")
//		Log.e("View1TextWidth=>>>>>", "${longText.paint.measureText(longText.text.toString()).toInt()}")
//		Log.e("HorizontalWidth=>>>>>", "${width}===")
//
//		//Solution2  but always from start
//		if (isStart.not())
//			Handler().postDelayed({
//				ObjectAnimator.ofInt(this, "scrollX", 1290-900).apply {
//					duration = 1400
//				}.start()
//				isStart = true
//			}, 1500)
////
//		Handler().postDelayed({
//			ObjectAnimator.ofInt(this, "scrollX", 1290+400).apply {
//				duration = 1500
//			}.start()
//			Log.e("Ruuuuu====", "kfokofko")
//		}, 3000)
//
//		Handler().postDelayed({
//			ObjectAnimator.ofInt(this, "scrollX", 1290+400).apply {
//				duration = 1500
//			}.start()
//			Log.e("Ruuuuu====", "kfokofko")
//		}, 3000)
//	}
//
//
//}