package com.whz.myjetpack.view

import android.content.DialogInterface
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.whz.myjetpack.R
import com.whz.myjetpack.databinding.ActivityEventBinding
import com.whz.myjetpack.lifecycle.MyLifecycleObserver
import com.whz.myjetpack.utils.BitmapUtils
import com.whz.myjetpack.vm.EventViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

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
        //Lifecycle  生命周期-getLifecycle()添加观察者
        lifecycle.addObserver(MyLifecycleObserver())
        dataBind = DataBindingUtil.setContentView(this, R.layout.activity_event)
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(
            EventViewModel::class.java
        )
        val testHandler = TestHandler()
        dataBind.handler = testHandler
        testHandler.obClickListener.set(View.OnClickListener {
            Toast.makeText(this@EventActivity, "Observable绑定成功", Toast.LENGTH_SHORT).show()
        })
        //展示Dialog
        val dialog = AlertDialog.Builder(this)
            .setTitle("子线程展示")
            .setMessage("虚怀如谷，大智若愚")
            .setPositiveButton("确定", DialogInterface.OnClickListener({ dialog, which ->
                Toast.makeText(this@EventActivity, "点击", Toast.LENGTH_SHORT).show()
            }))
        //引入Kotlin的线程,使用thread函数进行创建
        kotlin.concurrent.thread(name = "王怀智") {
            //            Thread.sleep(5000)
//            println(Thread.currentThread().toString() + "子线程")
//            //使用databind之后可以在子线程中进行修改
////            dataBind.button6.text = "子线程运行"
//            //防止子线程不能运行dialog，使用Looper.prepare,
//            Looper.prepare()//循环初始化
//            dialog.show()
//            Looper.loop()
        }

        //管理生命周期的方法
        dataBind.viewmodel = viewModel
        dataBind.lifecycleOwner = this
        viewModel.nameTest.value = "就看见"
        thread {
            val bitmap =
                BitmapUtils.parseUrl("https://tpc.googlesyndication.com/simgad/1003817915554331183/downsize_200k_v1")
            runOnUiThread { dataBind.imageView.setImageBitmap(bitmap) }
        }
    }

    /**
     * 内部类
     * DataBinding绑定事件的几种方式
     */
    inner class TestHandler {
        //----------1.@{函数}
        fun onClickListener1(view: View) {
            Toast.makeText(this@EventActivity, "@{onClickListener1}绑定成功", Toast.LENGTH_SHORT).show()
        }

        //----------2.@{()->函数()},不带参数
        fun onClickListener2() {
            Toast.makeText(this@EventActivity, "@{()->onClickListener2()}绑定成功", Toast.LENGTH_SHORT).show()
        }

        //----------3.@{类::函数}
        fun onClickListener3(view: View) {
            Toast.makeText(this@EventActivity, "@{::onClickListener3}绑定成功", Toast.LENGTH_SHORT).show()
        }

        //----------4.@{()->函数(参数)} 带参数
        fun onClickListener4(aint: Int) {
            Toast.makeText(
                this@EventActivity, "@{()->onClickListener4(参数)}绑定成功---" +
                        aint, Toast.LENGTH_SHORT
            ).show()
        }

        //----------5.ObservableFeild<OnClickListener> 可观察对象
        val obClickListener = ObservableField<View.OnClickListener>()

    }
}