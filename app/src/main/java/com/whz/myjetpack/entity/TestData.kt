package com.whz.myjetpack.entity

import androidx.databinding.ObservableField

/**
 * Created by 王鹏程 on 2020/4/20.
 * 类(TestData)解释:
 * 绑定单个可观察变量
 */
class TestData {
    var name=""//此变量不予监听
    var title = ObservableField<String>()
}