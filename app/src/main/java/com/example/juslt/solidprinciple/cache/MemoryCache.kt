package com.example.juslt.solidprinciple.cache

import android.graphics.Bitmap
import android.util.LruCache

/**
 * Created by Juslt on 2019/3/26
 * 内存缓存
 * 职责单一原则
 */
class MemoryCache : ImageCache {
    //LRUCache
    private var mImageCache: LruCache<String, Bitmap>? = null

    init {
        //计算可以使用的最大值
        val maxSize = (Runtime.getRuntime().maxMemory() / 1024).toInt()
        //取四分之一可用内存作为缓存
        mImageCache = object : LruCache<String, Bitmap>(maxSize / 4) {
            override fun sizeOf(key: String?, value: Bitmap?): Int {
                return value!!.rowBytes * value.height / 1024
            }
        }
    }

    override fun putBitmap(key: String, bitmap: Bitmap) {
            mImageCache!!.put(key, bitmap)
    }

    override fun getBitmap(key: String): Bitmap? {
        return mImageCache!!.get(key)
    }

}