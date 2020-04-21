package com.whz.myjetpack.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.whz.myjetpack.R
import com.whz.myjetpack.databinding.ActivityEventBinding
import com.whz.myjetpack.vm.EventViewModel

/**
 * Created by 王鹏程 on 2020/4/21.
 * 类(EventActivity)解释:
 *
 */
class EventActivity : AppCompatActivity() {
    lateinit var dataBind: ActivityEventBinding
    lateinit var viewModel: EventViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBind = DataBindingUtil.setContentView(this, R.layout.activity_event)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[EventViewModel::class.java]
    }
}