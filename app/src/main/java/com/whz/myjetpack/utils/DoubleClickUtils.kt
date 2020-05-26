package com.whz.myjetpack.utils

import android.os.SystemClock

/**
 * Created by 王鹏程 on 2020/5/15.
 * 类(DoubleClickUtils)解释:
 * 防止双击
 */
class DoubleClickUtils{
    var startTime: Long = 0
    fun isDoubleCheck(): Boolean {
        System.nanoTime()
        val endTime = SystemClock.elapsedRealtime();
        if ((endTime-startTime)>1000){
            LogUtils.d((endTime-startTime).toString())
            startTime=endTime
            return true
        }
        return false

    }

    fun setStartTime() {
        startTime = System.nanoTime()
    }

}