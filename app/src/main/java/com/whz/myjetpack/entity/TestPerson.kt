package com.whz.myjetpack.entity

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.whz.myjetpack.BR

/**
 * Created by 王鹏程 on 2020/4/20.
 * 类(TestPerson)解释:
 *  继承BaseObservable，绑定对象/实体类
 */
class TestPerson : BaseObservable() {
    @get:Bindable
    var country: String = ""
        set(value) {
            field = value
//            notifyChange()

            /*
            注：kotlin实现MVVM框架使用DataBinding，报unresolved reference:BR的解决方法：
            https://blog.csdn.net/weixin_40929353/article/details/102911137
             */
            notifyPropertyChanged(BR.country)
        }
//    var sex: String = ""
}