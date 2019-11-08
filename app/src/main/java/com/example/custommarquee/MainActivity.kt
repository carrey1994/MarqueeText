package com.example.custommarquee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Scroller
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
	
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		marqueeFrame.setData(
			listOf(
				"abcde",
				"ZZZZZ"
			))
//		startActivity(Intent(this, AnotherActivity::class.java))
//		marqueeFrame.invalidate()
		
		
	}
}
