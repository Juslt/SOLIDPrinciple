package com.example.juslt.solidprinciple

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.LruCache
import android.widget.ImageView
import com.example.juslt.solidprinciple.cache.DoubleCache
import com.example.juslt.solidprinciple.cache.ImageCache
import com.example.juslt.solidprinciple.cache.MemoryCache
import com.example.juslt.solidprinciple.cache.SDCardCache
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executors

/**
 * Created by Juslt on 2019/3/26
 * 加载图片类
 */
class ImageLoader {
    //线程池 ,线程数量为CUP的数量
    private val executor by lazy { Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()) }

    private var mImageCache: ImageCache = MemoryCache()

    fun setCacheType(imageCache: ImageCache){
        mImageCache = imageCache
    }

    fun disPlayImage(imageView: ImageView, url: String) {

        //缓存是否存在
        var bitmap = mImageCache.getBitmap(url)
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap)
            return
        }

        imageView.tag = url
        executor.submit {
            //HttpUrlConnection下载图片
            bitmap = downloadImage(url) ?: return@submit
            //显示图片
            if (imageView.tag == url)
                imageView.setImageBitmap(bitmap)
            mImageCache.putBitmap(url, bitmap!!)
        }
    }

    private fun downloadImage(url: String): Bitmap? {
        var bitmap: Bitmap? = null
        val urls = URL(url)
        var conn: HttpURLConnection? = null
        try {
            conn = urls.openConnection() as HttpURLConnection
            bitmap = BitmapFactory.decodeStream(conn.inputStream)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            conn?.disconnect()
        }
        return bitmap
    }


}