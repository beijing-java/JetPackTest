package com.whz.livepermissions

import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData

/**
 * Created by 王鹏程 on 2020/4/24.
 * 类(LivePermissions)解释:
 *  //双重检查
 *  @Volatile private var instance:S?=null
 *
 *  ?:左侧非空，返回左侧，否则返回右侧，初始化，这样相当于做了判断，跟java中的双重锁差不多
 *  fun getInstance(){
 *      instance?:sychronized(this){
 *          instance?:S().also{
 *              instance=it
 *          }
 *      }
 *  }
 *
 *  demo:
 *  val permissions=LivePermissions(this)
 *  permissions.request()
 */
class LivePermissions {
    companion object {
        const val TAG = "permissions"
    }

    @Volatile
    private var liveFragment: FragmentPermission? = null

    constructor(activity: AppCompatActivity) {
        liveFragment = getInstance(activity.supportFragmentManager)
    }

    constructor(fragment: Fragment) {
        liveFragment = getInstance(fragment.childFragmentManager)
    }

    private fun getInstance(fragmentManager: FragmentManager) =
        liveFragment ?: synchronized(this) {
            //二次判断枷锁
            liveFragment ?: if (fragmentManager.findFragmentByTag(TAG) == null) {
                FragmentPermission().run {
                    fragmentManager.beginTransaction().add(this, TAG).commitNow()
                    this
                }
            } else {
                fragmentManager.findFragmentByTag(TAG) as FragmentPermission
            }
        }


    fun request(vararg permission: String): MutableLiveData<PermissionResult> {
        return this.requestArray(permission)
    }

    fun requestArray(permission: Array<out String>): MutableLiveData<PermissionResult> {
        liveFragment!!.requestPermissions(permission)
        return liveFragment!!.liveData
    }
}