package com.whz.myjetpack.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent

/**
 * Created by 王鹏程 on 2020/4/22.
 * 类(MyLifecycleObserver)解释:
 *
 */
class MyLifecycleObserver : LifecycleObserver {
    var nameLife: MutableLiveData<String> = MutableLiveData()
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public fun onCreate() {
        nameLife.value = "onCreate"
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public fun onPause() {
        nameLife.value = "onPause"
    }

    public fun getName(): MutableLiveData<String> {
        return nameLife
    }
}