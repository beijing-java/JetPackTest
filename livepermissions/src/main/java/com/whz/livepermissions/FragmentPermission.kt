package com.whz.livepermissions

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData

/**
 * Created by 王鹏程 on 2020/4/24.
 * 类(FragmentPermission)解释:
 *
 */

/*
这里顺带提下kotlin的可见性修饰
private 当前类可访问
protected 当前包下可访问
internal *** 新加 -- 模块内部可见
public 全部
 */

internal class FragmentPermission : Fragment() {
    //这里的liveData是重中之重，作为发送通知使用
    lateinit var liveData: MutableLiveData<PermissionResult>
    val PERMISSIONS_REQUEST_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //fragment保持状态，不会销毁，重新创建
        retainInstance = true
    }

    /*
    协变：A是B的子类，保留子类型化关系 C<A>是C<B>的子类，保留了子类型化
    kotlin中某个类型参数上是可以协变的，在该类型上添加out关键字
    协变-只读类型
     */
    fun requestPermissions(permissions: Array<out String>) {
        liveData = MutableLiveData()
        requestPermissions(permissions, PERMISSIONS_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == PERMISSIONS_REQUEST_CODE) {
            //拒绝还勾选了不再询问
            val denyPermission = ArrayList<String>()
            //只是拒绝，还显示UI
            val rationalePermission = ArrayList<String>()

            for ((index, value) in grantResults.withIndex()) {
                if (value == PackageManager.PERMISSION_DENIED) {
                    if (shouldShowRequestPermissionRationale(permissions[index])) {
                        rationalePermission.add(permissions[index])
                    } else {
                        denyPermission.add(permissions[index])
                    }
                }
            }
            if (rationalePermission.isEmpty() && denyPermission.isEmpty()) {
                liveData.value = PermissionResult.Grant
            } else {
                if (rationalePermission.isNotEmpty()) {
                    liveData.value = PermissionResult.Rationale(rationalePermission.toTypedArray())
                } else if (denyPermission.isNotEmpty()) {
                    liveData.value = PermissionResult.Deny(denyPermission.toTypedArray())
                }
            }
        }
    }
}