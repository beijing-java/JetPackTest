package com.whz.myjetpack.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * Created by 王鹏程 on 2020/5/7.
 * 类(MyFragmentAdapter)解释:
 *
 */
class MyFragmentAdapter(fm: FragmentManager, list: List<Fragment>, title: Array<String>) : FragmentPagerAdapter(fm) {
    lateinit var list: List<Fragment>
    lateinit var title: Array<String>

    init {
        this.list = list
        this.title = title
    }

    override fun getCount(): Int {
        return list?.size ?: 0
    }

    override fun getItem(position: Int): Fragment {
        return list?.get(position)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return title.get(position)
    }
}