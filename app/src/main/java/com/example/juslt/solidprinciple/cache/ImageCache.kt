package com.example.juslt.solidprinciple.cache

import android.graphics.Bitmap
import android.widget.ImageView

/**
 * Created by Juslt on 2019/3/26
 * 图片缓存接口
 *
 */
interface ImageCache {
    //put Image到缓存
    fun putBitmap(key:String,bitmap:Bitmap)
    //从缓存中get Image
    fun getBitmap(key: String):Bitmap?
}