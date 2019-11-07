package com.example.custommarquee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Scroller
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
	
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
//        marquee.startScroll()
		marqueeFrame.setData(listOf("あいうえお　さしすせそ　かきくけこ　たちつてと　なにぬねのなにぬ", "abcdefghijklmnopqrstuvwxyz !!!", "安安安安安安安安安安安@@", "あいうえお　さしすせそ　かきくけこ　たちつてと　なにぬねのなにぬ"))
//		marqueeFrame.invalidate()
		
		
	}
}
