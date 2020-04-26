package com.whz.livepermissions

/**
 * Created by 王鹏程 on 2020/4/24.
 * 类(PermissionResult)解释:
 *  sealed关键字  密封类,
 *  没有构造函数，不可以实例化
 *  拥有多个子类，只能子类实例化,子类可以拥有构造函数
 *  子类跟父类在同一个文件内
 */
sealed class PermissionResult {
    object Grant : PermissionResult()

    class Deny(val permissions: Array<String>) : PermissionResult()//拒绝，并不再询问
    class Rationale(val permissions: Array<String>) : PermissionResult()//拒绝，可以再次询问
}

