package com.whz.myjetpack.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.whz.myjetpack.R
import com.whz.myjetpack.customview.PorterDuffXfermodeView

/**
 * Created by 王鹏程 on 2020/5/7.
 * 类(FragmentOne)解释:
 *
 */
class FragmentOne : Fragment() {
    var activity: Context? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_one, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity = context
    }
}