package com.whz.myjetpack.view

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.qw.curtain.lib.Curtain
import com.qw.curtain.lib.IGuide
import com.whz.livepermissions.LivePermissions
import com.whz.livepermissions.PermissionResult
import com.whz.myjetpack.R
import com.whz.myjetpack.databinding.ActivityMainBinding
import com.whz.myjetpack.entity.TestData
import com.whz.myjetpack.entity.TestLiveData
import com.whz.myjetpack.entity.TestPerson
import com.whz.myjetpack.utils.DoubleClickUtils
import com.whz.myjetpack.utils.LogUtils
import com.whz.myjetpack.utils.ToastUtils
import com.whz.myjetpack.vm.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

/**
 *使用DataBinding绑定数据，基本有三种使用方式：
 * 一、使用Observable单一变量监听 例：[TestData]
 * 二、实体类继承BaseObservable，添加Bindable注解，实现get/set方法
 *   具体可查看：[TestPerson]
 * 三、配合LiveData进行绑定观察 [TestLiveData]
 *
 * 单向绑定：数据改变时UI相应改变
 * 双向绑定：数据改变对应UI改变，UI改变对应数据改变（EditText,Checkbox,Button等UI状态的改变绑定的数据相应的改变）
 *
 */
class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //对于Fragment，ListView，RecyclerView来说，使用inflate或者bind方法来进行加载
        // ActivityMainBinding.inflate或者使用DataBindingUtils.inflate
        //DataBindUtil.setContentView这种方式适用于activity
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[MainViewModel::class.java]
        binding.viewmodel = viewModel
        //管理生命周期的方法
        binding.lifecycleOwner = this

        val tv5: TextView = binding.textView5
        tv5.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG

        val a1 = binding.textView2.text.toString().toInt()
        val a2 = binding.textView21.text.toString().toInt()

        //------------演示自定义可观察单个变量ObservableXX
        var testData = TestData()
        binding.test = testData
        testData.title.set("王怀智")

        //------------绑定title--databind并不一定非要绑定Observable/LiveData，可以绑定普通数据
//        binding.title = "煎饼果子"

        //-------------演示绑定对象/实体类
        var testPerson = TestPerson()
        testPerson.country = "北京"
        binding.person = testPerson

        //-------------演示绑定LiveData
        var mLiveData = TestLiveData()
        mLiveData.setmData(1200)

        binding.mData = mLiveData

        var AutoName = "王怀智"
        //测试改变效果
        binding.button.setOnClickListener {
            viewModel.calculate(a1, a2)
            //通过set方法修改对应内容
            AutoName += AutoName
            testData.title.set(AutoName)

            testPerson.country = "河北石家庄"
            mLiveData.setmData(124545)
        }
        binding.button2.setOnClickListener {
            intentEvent()
        }
        val permissions = LivePermissions(this)
        permissions.request(android.Manifest.permission.CAMERA).observe(this, Observer {
            when (it) {
                is PermissionResult.Grant -> Toast.makeText(this, "权限允许", Toast.LENGTH_SHORT).show()
                is PermissionResult.Rationale -> {
                    Toast.makeText(this, "权限拒绝", Toast.LENGTH_SHORT).show()
                    it.permissions.forEach {
                        println("被拒绝的权限$it")
                    }
                }
                is PermissionResult.Deny -> {
                    Toast.makeText(this, "权限拒绝，且不再询问", Toast.LENGTH_SHORT).show()
                    it.permissions.forEach {
                        println("被拒绝的权限$it")
                    }
                }
            }
        })
        binding.button3.setOnClickListener {
                        startActivity(Intent(this, MaterialActivity::class.java))
        }
        val dc = DoubleClickUtils()
        button4.setOnClickListener {
            //            startActivity(Intent(this, NeScrollActivity::class.java))

            LogUtils.d(dc.isDoubleCheck().toString())
            LogUtils.d(dc.startTime.toString())

            ToastUtils.short("ToastUtils测试")
        }
        // 显示遮罩
        showCurtain()
        LogUtils.d("LogUtils测试")

//        val viewTreeObserver = binding.button.viewTreeObserver
//        viewTreeObserver.addOnPreDrawListener {
//            val width = binding.button.width
//            LogUtils.d(width.toString())
//
//            true
//        }


    }

    private fun showCurtain() {
        Curtain(this).run {
            with(imageView)
            withPadding(cl_iv_image, 24)
            setTopView(R.layout.curtain_header_main)
            setCallBack(object : Curtain.CallBack {
                override fun onDismiss(iGuide: IGuide?) {

                }

                override fun onShow(iGuide: IGuide?) {
                    iGuide?.let {
                        it.findViewByIdInTopView<View>(R.id.tv_know).setOnClickListener {
                            iGuide?.dismissGuide()
                        }
                    }
                }

            })
            show()
        }
    }

    /**
     * 这里对应的生命周期onResume，一般做的操作测量view的宽高
     */
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            val w = getContentView().measuredWidth
            val h = getContentView().measuredHeight
            println("测量view的高度1" + w + h)
        }
    }

    // 使用消息队列
    override fun onResume() {
        super.onResume()
        val view = getContentView()
        view.post {
            val w = view.measuredWidth
            val h = view.measuredHeight
            println("测量view的高度2" + w + h)
        }

    }

    public fun intentEvent() {
        val intent = Intent()
        intent.setClass(this, EventActivity::class.java)
        intent.putExtra("data", "intent传递的数据")
        startActivity(intent)
//        finish()
    }

    /**
     * 获取activity的contentView
     */
    internal fun getContentView(): View {
        //对应java中的 ViewGroup viewgrounp=(ViewGroup)getWindow().getDecorView()
        val view: ViewGroup = window.decorView as ViewGroup
        val contentView = view.getChildAt(0) as LinearLayout
        return contentView
    }


}
