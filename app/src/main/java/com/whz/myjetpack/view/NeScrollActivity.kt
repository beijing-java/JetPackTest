package com.whz.myjetpack.view

import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.AppBarLayout
import com.whz.myjetpack.R
import com.whz.myjetpack.adapter.MyFragmentAdapter
import com.whz.myjetpack.entity.CollapsingToolbarLayoutState
import com.whz.myjetpack.fragment.FragmentOne
import com.whz.myjetpack.fragment.FragmentTwo
import kotlinx.android.synthetic.main.activity_nescroll.*

/**
 * Created by 王鹏程 on 2020/5/7.
 * 类(NeScrollActivity)解释:
 *
 */
class NeScrollActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    var state: CollapsingToolbarLayoutState? = null
    val listStr = arrayOf("android", "ios", "python", "web")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nescroll)

        setSupportActionBar(act_nescroll_coll_toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbar = findViewById(R.id.act_nescroll_coll_toolbar)
        toolbar.setNavigationIcon(R.mipmap.ic_back)

        val listFragment = mutableListOf<Fragment>()
        listFragment.add(FragmentOne())
        listFragment.add(FragmentTwo())
        listFragment.add(FragmentOne())
        listFragment.add(FragmentTwo())
        val adapter = MyFragmentAdapter(supportFragmentManager, listFragment, listStr)
        act_scroll_viewpager.adapter = adapter
        act_scroll_tablayout.setupWithViewPager(act_scroll_viewpager)

        // 监听appbarlayout事件  verticalOffset 垂直偏移量
        act_nescroll_appbar.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            override fun onOffsetChanged(p0: AppBarLayout?, verticalOffset: Int) {
                when {
                    verticalOffset == 0 -> {
                        if (state != CollapsingToolbarLayoutState.EXPANDED) {
                            state = CollapsingToolbarLayoutState.EXPANDED
                            act_nescroll_collapsingtbl.title = "展开的"
                        }
                    }
                    Math.abs(verticalOffset) >= act_nescroll_appbar.totalScrollRange -> {
                        if (state != CollapsingToolbarLayoutState.COLLAPSED) {
                            state = CollapsingToolbarLayoutState.COLLAPSED
                            act_nescroll_collapsingtbl.title = "折叠的"
                            act_nescroll_coll_tb_tv.visibility = View.VISIBLE
                        }
                    }
                    state != CollapsingToolbarLayoutState.INTERNEDIATE -> {
                        if (state == CollapsingToolbarLayoutState.COLLAPSED) {
                            act_nescroll_coll_tb_tv.visibility = View.GONE
                        }
                        act_nescroll_collapsingtbl.title = "正在展开"
                        state = CollapsingToolbarLayoutState.INTERNEDIATE
                    }
                }


            }

        })

    }
}