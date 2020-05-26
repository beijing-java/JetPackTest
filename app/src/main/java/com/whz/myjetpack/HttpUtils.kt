package com.whz.myjetpack

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.HttpParams
import com.lzy.okgo.model.Response
import java.lang.reflect.Array

/**
 * Created by 王鹏程 on 2020/5/14.
 * 类(HttpUtils)解释:
 *
 */
class HttpUtils : LifecycleObserver {
    companion object {
        var tag: Any? = null
    }


    fun get(url: String, tags: Any = "httpUtils", callback: StringCallback) {
        tag = tags
        OkGo.get<String>(url)
            .tag(tag)
            .execute(callback)
    }

    fun post(url: String, tags: Any = "httpUtils", params: HttpParams, callback: StringCallback) {
        tag = tags
        OkGo.post<String>(url)
            .tag(tag)
            .params(params)
            .execute(callback)
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        //tag传false，表示不取消请求
        if (tag is Boolean && tag == false) {
            return
        } else {
            OkGo.getInstance().cancelTag(tag)
        }
    }
}