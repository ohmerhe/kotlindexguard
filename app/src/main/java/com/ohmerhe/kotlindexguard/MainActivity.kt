package com.ohmerhe.kotlindexguard

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    lateinit var imageSwitchHelper: ViewPageSwitchBgHelper

    lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageView = findViewById(R.id.image_view) as ImageView

        imageSwitchHelper = ViewPageSwitchBgHelper(imageView)

        imageSwitchHelper.swtichBgImageAnim(ColorDrawable(Color.YELLOW))
    }
}
