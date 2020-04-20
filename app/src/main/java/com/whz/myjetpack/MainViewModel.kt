package com.whz.myjetpack

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by 王鹏程 on 2020/4/16.
 * 类(MainViewModel)解释:
 *
 */
class MainViewModel : ViewModel() {
    var number: MutableLiveData<String> = MutableLiveData()
    var total: MutableLiveData<Int> = MutableLiveData()

    var fullName: MutableLiveData<String> = MutableLiveData()
    val currentName: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    /**
     * 计算总价
     */
    public fun calculate(vararg num: Int) {
        val number = number.value ?: 0
        var numTotal: Int = 0
        if (num.size > 0) {
            for (i in num) {
                numTotal += i
            }
        }
        total.value = number.toString().toInt() * numTotal
    }

}