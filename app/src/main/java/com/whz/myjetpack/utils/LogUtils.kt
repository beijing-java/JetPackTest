package com.whz.myjetpack.utils

import android.util.Log
import java.util.*

/**
 * Created by 王鹏程 on 2020/5/15.
 * 类(LogUtils)解释:
 *
 */

object LogUtils {

    private var className: String = "";
    private var methodName: String = "";
    private var lineName: Int = 0
    public const val DEBUG = true
    private const val TAG = "山舟网络"
    private fun createLog(log: String): String {

        val buffer = StringBuffer()
        buffer.apply {
            append("[$methodName")
            append("($className")
            append(":$lineName)]")
            append(log)
        }
        return buffer.toString()
    }

    private fun processTagAndHead(): String {
        val elements = Thread.currentThread().stackTrace
        val offset = getProcessOffset(elements)
        val targetElement = elements[offset]
        val head = Formatter()
            .format(
                "%s [%s(%s:%d)] ",
                Thread.currentThread().name,
                targetElement.methodName,
                targetElement.fileName,
                targetElement.lineNumber
            )
        return head.toString()
    }

    private fun getProcessOffset(sElements: Array<StackTraceElement>): Int {
        var i = 3
        while (i < sElements.size) {
            val e = sElements[i]
            if (e.className != LogUtils::class.java.name) {
                return i
            }
            i++
        }
        return i
    }

    fun e(message: String) {
        if (DEBUG) {
            Log.e(TAG,processTagAndHead()+message)
        }
    }

    fun i(message: String) {
        if (DEBUG) {
            Log.i(TAG,processTagAndHead()+message)
        }
    }
    @JvmStatic
    fun d(message: String) {
        if (DEBUG) {
            Log.d(TAG,processTagAndHead()+message)
        }
    }

    fun v(message: String) {
        if (DEBUG) {
            Log.v(TAG,processTagAndHead()+message)
        }
    }

    fun w(message: String) {
        if (DEBUG) {
            Log.w(TAG,processTagAndHead()+message)
        }
    }

}