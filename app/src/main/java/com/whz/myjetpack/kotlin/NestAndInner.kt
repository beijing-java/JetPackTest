package com.whz.myjetpack.kotlin

import android.view.View

/**
 * Created by 王鹏程 on 2020/4/21.
 * 类(NestAndInner)解释:
 *  注：
 *  kotlin的嵌套类，内部类
 *  嵌套类（静态内部类，对应java中static class）
 *  内部类（非静态内部类，使用时需要实例化外部类）
 */
class NestAndInner {
    //定义静态常量，以及静态方法
    companion object {
        val testName: String = "王怀智"
    }
    //定义非静态常量
    val testName: String = "赵子龙"

    //嵌套类
    class TestNest {
        //testName是需要定义在companion object中，设定为静态常量
        fun getName(): String = testName
    }

    //内部类
    inner class TestInner {
        //可以访问静态/非静态变量，函数
        fun getName(): String = testName
    }
    //匿名内部类
    fun textObject(view:View){
        view.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                // 这里的object : Interface接口，为匿名内部类
            }

        })
    }
}