package com.whz.myjetpack.kotlin.coroutines

import kotlinx.coroutines.*
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * Created by 王鹏程 on 2020/4/27.
 * 类(TestCoroutines)解释:
 * GlobalScope.launch  launch  runBlocking coroutineScope 创建一个新的协程作用域
 * async:async必须在协程作用域中定义 配合await返回结果
 * withContext()是async跟await的封装
 */
class TestCoroutines {
    var obj: Job = Job()

    fun createCoroutine() {
        // 使用CoroutineScope创建，CoroutineScope为函数
        val scope = CoroutineScope(obj)
        obj = scope.launch {
            // launch 无返回值，只是运行一段程序,所以返回值取值
            val result = async {
                5 + 5
            }.await()
            println(result)
        }

    }

    fun createCoroutins() {
        // 创建顶层协程作用域，为什么不建议使用顶层，因为每次cancel，只能取消一个，多个协程，挨个取消
        obj = GlobalScope.launch {
        }
    }

    fun cancelCoroutins() {
        obj.cancel()// 取消协程--activity销毁时，将协程取消
    }
}

fun main() {
//    GlobalScope.launch(block={
//        println("codes run in coroutins scope")
//        delay(1500) // 让当前的协程延迟指定时间后再运行
//        println("coroutins restart")
//    })
//    println("the line runs first")
//    Thread.sleep(1000)

    /*
    runBlocking 不建议使用在协程中，仅仅作为测试使用
    阻塞线程，等待协程完成
     */
//    runBlocking {
//        println("runBlocking is run")
//        delay(2000)
//        println("restart")
//    }
    /*
    协程的并发操作，建立多个子协程launch{}
     */
//    runBlocking {
//        // 多个协程并发状态
//        launch {
//            println("runBlocking1 is run")
//            delay(2000)
//            println("restart1")
//        }
//        launch {
//            println("runBlocking2 is run")
//            delay(2000)
//            println("restart2")
//        }
//    }

//    val startTime=System.currentTimeMillis()
//    runBlocking {
//        // repeat循环执行次数，再次循环创建协程
//        repeat(100000){
//            launch {
//                println(".")
//            }
//        }
//    }
//
//    val endTime=System.currentTimeMillis()
//    println(endTime-startTime)// 10万个协程运行时间毫秒级完成

    /*
    单独抽离函数，在launch中进行调用，
    suspend
     */
//    runBlocking {
//        suFun("赵四")
//        suFun("王五")
//        suFun("孙六")
//    }

//    val mCoroutine = TestCoroutines()
//    println(mCoroutine.createCoroutine())

//    runBlocking {
//        val result = async {
//            delay(1000)
//            5 + 5
//        }
////        println(result)
//                println(result.await())
//
//        val result2 = async {
//            delay(1000)
//            10 + 10
//        }
//        /*
//         一种方式是串行方式---会阻塞当前协程 async直接调用await
//         一种方式是并行方式---快速，省时     async先执行，最后调用await
//         */
////        println(result2)
//        println(result2.await())
//    }

//    runBlocking {
//        // Dispatchers 调度员
//        val result = withContext(Dispatchers.Default) {
//            delay(1000)
//            "王怀智学习kotlin的协程"
//        }
//        println(result)
//    }

   println(request(""))
}

/*
    suspend 定义挂起的函数，挂起函数是可以相互调用的
    Suspend function 'delay' should be called only from a coroutine or another suspend function
    挂起函数'delay'只能再协程或者另一个挂起函数中进行调用

    coroutineScope 创建协程，继承外部的协程作用域创建一个子作用域，给挂起函数创建协程作用域
    按顺序执行，执行完内部协程，挂起之后再进行下一个协程
 */
suspend fun suFun(name: String) = coroutineScope {
    launch {
        println("suspend function 执行$name")
        delay(1500)
    }
}

fun comFun() {
    println("common function 执行")
}

fun request(str: String): String {
    var s: String = ""
    runBlocking {
        delay(1000)
        s = suspendCoroutine<String> { continuation ->
            continuation.resume("正确")
        }
    }
    return s
}