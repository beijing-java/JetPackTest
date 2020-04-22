package com.whz.myjetpack.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * Created by 王鹏程 on 2020/4/22.
 * 类(MyLifecycleObserver)解释:
 *
 */
class MyLifecycleObserver : LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public fun onCreate() {
        println("onCreate")
    }


}