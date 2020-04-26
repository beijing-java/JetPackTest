package com.whz.myjetpack.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.IOException
import java.io.InputStream
import java.net.MalformedURLException
import java.net.URL

/**
 * Created by 王鹏程 on 2020/4/23.
 * 类(BitmapUtils)解释:
 *
 */
object BitmapUtils {
    /**
     *
     * url地址转化为Bitmap
     */
    fun parseUrl(url: String): Bitmap? {
        var bitmap: Bitmap? = null
        try {
            val parUrl = URL(url)
            val http = parUrl.openConnection()
            http.doInput = true
            http.connect()
            val inputStream = http.getInputStream()
            bitmap = BitmapFactory.decodeStream(inputStream)
        }catch (e:MalformedURLException){
            e.printStackTrace()
        }catch (e:IOException){
            e.printStackTrace()
        }
        return bitmap
    }
}