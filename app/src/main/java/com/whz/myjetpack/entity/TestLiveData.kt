package com.whz.myjetpack.entity

import androidx.lifecycle.MutableLiveData

/**
 * Created by 王鹏程 on 2020/4/20.
 * 类(TestLiveData)解释:
 *
 */
class TestLiveData {
    var hData: MutableLiveData<Int> = MutableLiveData<Int>()

    public fun setmData(i: Int) {
        hData.value = i
    }
}