package com.whz.myjetpack.entity

import java.lang.StringBuilder

/**
 * Created by 王鹏程 on 2020/4/27.
 * 类(TestEnum)解释:
 * Enum（枚举类）
 * 1. 定义每一个常量（对象）
 * 2. 定义构造函数
 * 3. 定义抽象方法
 */
enum class TestEnum {
    RED, WHITE, BLACK, GREEN
}

/**
 * constructor构造函数
 * 主一多次
 * init最先调用，进行初始化操作
 */
enum class TestEnum1 {
    RED(0xFF0000, "哈哈") {
        override fun print() {
            println("红色")
        }
    },
    WHITE(0xFFFFFF) {
        override fun print() {
            println("白色")
        }
    },
    BLACK(0x000000) {
        override fun print() {
            println("黑色")
        }
    },
    GREEN(0x00FF00) {
        override fun print() {
            println("绿色")
        }
    };

    var argb: Int

    // 定义构造函数
    constructor(argb: Int) {
        this.argb = argb
    }

    constructor(argb: Int, name: String) : this(argb) {

    }

    // 定义抽象方法
    abstract fun print()
}
/*
使用方式：
在java中，一般是配合switch来进行区分
定义构造参数，获取参数值
 */
/**
 * 这是一种使用方式
 */
fun toEnum(t: TestEnum) {
    when (t) {
        TestEnum.RED -> println("此颜色为红色")
        TestEnum.WHITE -> println("此颜色为白色")
        TestEnum.BLACK -> println("此颜色为黑色")
        TestEnum.GREEN -> println("此颜色为绿色")
        else -> println("没有这款颜色")
    }
}

fun main() {
    toEnum(TestEnum.BLACK)
    println(TestEnum1.RED.argb)
    println(TestEnum1.RED.print())

//    val s:String="a,b,c,,"
//    val arr=s.split(",")
//    println(arr.size)
//
//    val strBuilder=StringBuilder()
//    for (i in 1..10){
//        strBuilder.append(i)
//    }
//    println(strBuilder.toString())
}