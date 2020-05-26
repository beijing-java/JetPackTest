package com.whz.myjetpack.utils

import android.view.Gravity
import android.widget.Toast
import com.whz.myjetpack.MyApplication

/**
 * Created by 王鹏程 on 2020/5/15.
 * 类(ToastUtils)解释:
 *
 */
object ToastUtils {

    private fun initToast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT, gravity: Int) {
        var toast = Toast.makeText(MyApplication.instance, message, duration)
        toast.setGravity(gravity, 0, 0)
        toast.show()
    }

    // 短时间显示
    @JvmOverloads
    @JvmStatic
    fun short(message: String, gravity: Int = Gravity.BOTTOM) {
        initToast(message, gravity = gravity)
    }

    // 长时间显示
    @JvmOverloads
    @JvmStatic
    fun long(message: String, gravity: Int = Gravity.BOTTOM) {
        initToast(message, duration = Toast.LENGTH_LONG, gravity = gravity)
    }
}