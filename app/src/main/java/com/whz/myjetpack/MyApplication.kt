package com.whz.myjetpack

import android.app.Application
import com.lzy.okgo.OkGo
import com.lzy.okgo.cache.CacheMode
import okhttp3.OkHttpClient

/**
 * Created by 王鹏程 on 2020/5/14.
 * 类(MyApplication)解释:
 *
 */
class MyApplication : Application() {
    companion object {
        lateinit var instance: MyApplication
    }

    override fun onCreate() {
        super.onCreate()
        OkGo.getInstance().init(this)
        instance = this
    }
}