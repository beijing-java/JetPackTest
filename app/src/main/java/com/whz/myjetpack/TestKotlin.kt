package com.whz.myjetpack

//import引入扩展函数即可
import com.whz.myjetpack.kotlin.spreadfun.lastChar
import com.whz.myjetpack.kotlin.spreadfun.lastChar1

/**
 * Created by 王鹏程 on 2020/5/6.
 * 类(TestKotlin)解释:
 *  [TestJava]来进行java与kotlin之间的调用
 */
class TestKotlin {
    //lateinit 不支持基本元素（Int,Boolean,Char...）,修饰var的变量
    //lazy修饰val的变量

    //    lateinit var s:Boolean
    val testStr: String by lazy {
        "施瓦辛格"
    }


    /*
    这里的参数默认值，如果是kotlin调用，则相当于java中的方法重载
    而如果是java调用此方法时，是没有默认参数的，每个参数必须传，
    想让java调用方法重载，则需要加入@JvmOverloads 注解
     */
    @JvmOverloads
    fun <T> joinToString(
        collection: Collection<T>,
        separator: String = ",",
        prefix: String = "",
        postfix: String = ""
    ): String {
        val result = StringBuilder(prefix)
        // 通常写法
//        for ((index,element) in collection.withIndex()){
//            if (index>0)result.append(separator)
//            result.append(element)
//        }
//        result.append(postfix)
//        return result.toString()

        // 使用标准函数库进行修改
        return result.run {
            for ((index, element) in collection.withIndex()) {
                if (index > 0) append(separator)
                append(element)
            }
            append(postfix)
            toString()
        }
    }
}

fun main() {
    val test = TestKotlin()
    val list = listOf("王", "怀", "智")
    println(
        test.joinToString(list, prefix = "【", postfix = "】")
    )
    println("王怀智".lastChar())
    println("王怀智".lastChar1)
    twoToThree(sum2)
    println(pricingManner()(80))
}

/**
 * 装饰器模式：重新定义一个相同类型的类，实现相同接口，使用另一个对象作为变量，来进行调用
 * kotlin是通过by关键字来交给对象使用
 */
class CountingSet<T>(val innerSet: MutableCollection<T> = HashSet<T>()) : MutableCollection<T> by innerSet {
    var count: Int = 0;


    override fun add(element: T): Boolean {
        count++
        return innerSet.add(element)
    }

    override fun addAll(elements: Collection<T>): Boolean {
        count += elements.size
        return innerSet.addAll(elements)
    }
}

//高阶函数----定义为变量，或者定义为参数,还可以定义为返回值
val sum = { x: Int, y: Int -> x + y }
val sum2: (Int, Int) -> Int = { x, y -> x + y }

fun twoToThree(operator: (Int, Int) -> Int) {
    println(operator(5, 6))
}

/**
 * when的使用坑：
 * 1.当使用when来代替java中的swith时，只能做对应操作，不可以进行大小比较
 * 2.当使用when来代替java中的if时，可以不传参数，直接进行判断
 */
fun pricingManner(): (Int) -> Double {
    return {
        when {
            it >= 30 && it < 60 -> {
                0.5 * it
            }
            it >= 60 -> {
                0.8 * it
            }
            else -> {
                (1 * it).toDouble()
            }
        }
    }
}