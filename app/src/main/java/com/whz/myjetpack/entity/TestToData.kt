package com.whz.myjetpack.entity

/**
 * Created by 王鹏程 on 2020/5/6.
 * 类(TestToData)解释:
 * model类：加上data关键字，会自动生成toString，hashCode，set/get方法，equals
 * 不加data也是可以的
 */
class TestToData(val a: Int, val b: Int, var c: String, var d: String)

data class TestToData1(val a: Int, val b: Int, var c: String, var d: String)