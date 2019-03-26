package com.example.juslt.solidprinciple

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.juslt.solidprinciple.cache.DoubleCache
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageUrl = "http://weshop-oss.oss-cn-hangzhou.aliyuncs.com/1547088934246.png"
        val imageLoader = ImageLoader()
        imageLoader.setCacheType(DoubleCache())
        imageLoader.disPlayImage(imageView,imageUrl)
    }
}
