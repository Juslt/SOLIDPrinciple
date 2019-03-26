package com.example.juslt.solidprinciple.cache

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception

/**
 * Created by Juslt on 2019/3/26
 * sdcard缓存
 */
class SDCardCache : ImageCache {
    override fun putBitmap(key: String, bitmap: Bitmap) {
        val filePath = Environment.getExternalStorageDirectory().absolutePath + "/image"
        val file = File(filePath)
        if (!file.exists()) {
            file.mkdir()
        }

        val strings = key.split("/")
        val imagePath = "$filePath/${strings[strings.size - 1]}"
        var fileOutputStream: FileOutputStream? = null
        try {
            fileOutputStream = FileOutputStream(imagePath)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            fileOutputStream?.close()
        }
    }

    override fun getBitmap(key: String): Bitmap? {
        val filePath = Environment.getExternalStorageDirectory().absolutePath + "/image"
        val strings = key.split("/")
        val imagePath = "$filePath/${strings[strings.size - 1]}"
        if(!File(imagePath).exists()){
            return null
        }
        return BitmapFactory.decodeFile(imagePath)
    }

}