package com.whz.myjetpack.vm

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel

/**
 * Created by 王鹏程 on 2020/4/21.
 * 类(EventViewModel)解释:
 *
 */
class EventViewModel(app: Application) : AndroidViewModel(app) {
    val applications = app
    fun onClick1() {
        Toast.makeText(applications, "王怀智1", Toast.LENGTH_SHORT).show()
    }
}