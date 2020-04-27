package com.whz.myjetpack.kotlin.lambda

import kotlin.reflect.KFunction

/**
 * Created by 王鹏程 on 2020/4/26.
 * https://www.jianshu.com/p/498792258d91
 * 类(TestLambda)解释:
 * kotlin中的Lambda表达式学习
 * 穿插有inline(内联函数)
 *
 */
data class Person(val name: String, val age: Int) {
    override fun toString(): String {
        return super.toString()
    }

    //成员函数
    fun memberFun(a:Int,b:Int) {
        println("她的名字叫$name,芳龄是$age")
    }
}

val listPerson = listOf(Person("赵二", 12), Person("张三", 15), Person("李四", 20))


//命名函数
fun namFunction(a1: Int = 10, operation: String, a2: Int = 12): Int {
    return when (operation) {
        "+" -> a1 + a2
        "-" -> a1 - a2
        else -> {
            println("非法操作")
            1
        }
    }
}

/**
 * 对于inline的理解
 * inline内联函数，解决什么问题？
 * 调用方法会经历入栈出栈的过程，多次调用会造成不必要的性能消耗。当使用inline关键字之后，
 * 方法调用时就会被转化成一条条语句，不会经历栈的出入。
 */

fun converRep(conver: (String) -> Unit) {}

inline fun meetInline(s: String, noinline conver: (String) -> Unit) {
    /*
    Illegal usage of inline-parameter 'conver' in 'public inline fun meetInline(s: String, conver: () -> Unit): Unit defined in com.whz.myjetpack.kotlin.lambda in file TestLambda.kt'. Add 'noinline' modifier to the parameter declaration
     不可以把加inline的参数，传递给非inline的函数调用，如果需要调用，请加上"noinline"
     */
    converRep(conver)
}

fun main() {
    /*
    maxBy：传入参数：函数(element) -> item
           返回结果：T
    lambda是函数或者属性委托，可以用成员引用替换
     */
    //-----------1 当做参数进行传递
    val result = listPerson.maxBy({
        it.age
    })
    val intList = listOf(1, 2, 3, 10)
    // lambda省略参数，it代指
    intList.maxBy {
        it //it最好是指明参数类型
    }

    // lambda 常规写法，带有参数   参数 -> 操作/最后一行返回值
    listPerson.maxBy({ p: Person -> p.age })
    listPerson.maxBy(Person::age)

    //-------------------1-1 lambda 使用命名参数
    val transformName = listPerson.joinToString(separator = "=", transform = {
        it.name + "王怀智"
    })

    //-------------2 当做变量值来使用
    val sum = { x: Int -> Unit }

    meetInline("王怀智", { 12 })

    println(namFunction(operation = "+"))

    //-------------3 ***成员引用   调用函数中的成员方法/变量   Person::成员
    /*这里调用函数时，不需要加(),当lambda有多个参数时，可以直接使用调用成员，简单快捷*/
    val getMember = Person::memberFun
    getMember(Person("小刚",15),12,12)
    //等价于
    val getMember1 = { p: Person -> p.memberFun(12,12) }
    getMember1(Person("小红",18))



}
//----------编写lambda表达式为参数，以及它的调用方式
fun toLambda(i:Int,b:(Int)->Unit){
    println(b(i))
}
fun fromLambda(i:Int)= println("Salute")
//    ::定义的函数，参数值，以及数量必须要跟调用的函数中的lambda的参数对应
//    toLambda(1,::fromLambda)
