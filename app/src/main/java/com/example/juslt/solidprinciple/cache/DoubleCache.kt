package com.example.juslt.solidprinciple.cache

import android.graphics.Bitmap

/**
 * Created by Juslt on 2019/3/26
 * 融合内存缓存和本地缓存方式
 */
class DoubleCache:ImageCache{
    private val memoryCache = MemoryCache()
    private val sdCardCache = SDCardCache()
    override fun putBitmap(key: String, bitmap: Bitmap) {
        memoryCache.putBitmap(key,bitmap)
        sdCardCache.putBitmap(key,bitmap)
    }

    override fun getBitmap(key: String): Bitmap? {
        var bitmap = memoryCache.getBitmap(key)
        if(bitmap==null){
            bitmap = sdCardCache.getBitmap(key)
        }
        return bitmap
    }

}