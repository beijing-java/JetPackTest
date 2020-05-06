@file :JvmName("StringKt")

package com.whz.myjetpack.kotlin.spreadfun

/**
 * Created by 王鹏程 on 2020/5/6.
 * 类(TestSpreadFun)解释:
 * 扩展函数与扩展属性的定义
 *
 */

/*
定义扩展属性
 */
var String.lastChar1: Char
    get() = if (this.length > 0) {
        this.get(this.length - 1)
    } else {
        'a'
    }
    set(value: Char) {
        println(lastChar1)
    }

/*
扩展方法
定义最后一个字符的内容
 */
fun String.lastChar(): Char = this.get(this.length - 1)


