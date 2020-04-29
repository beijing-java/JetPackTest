package com.whz.myjetpack.view

import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.broooapps.graphview.CurveGraphConfig
import com.broooapps.graphview.models.GraphData
import com.broooapps.graphview.models.PointMap
import com.whz.myjetpack.R
import kotlinx.android.synthetic.main.activity_material_design.*
import kotlinx.coroutines.*

/**
 * Created by 王鹏程 on 2020/4/28.
 * 类(MaterialActivity)解释:
 *
 */
class MaterialActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material_design)
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)

        }
        curveGraphView.configure(
            CurveGraphConfig.Builder(this)
                .setAxisColor(R.color.Blue)// 轴的颜色
                .setGuidelineColor(R.color.Red) // 参考线颜色
                .setxAxisScaleTextColor(R.color.Black) // x,y轴的文字颜色
                .setyAxisScaleTextColor(R.color.Black)
                .setIntervalDisplayCount(5)
                .setNoDataMsg("没有数据").build()
        )
        //添加X,Y轴数据
        val pointMap = PointMap()
        pointMap.addPoint(0, 100)
        pointMap.addPoint(1, 200)
        pointMap.addPoint(2, 400)
        pointMap.addPoint(3, 200)
        pointMap.addPoint(4, 200)
        pointMap.addPoint(5, 800)
        val gd = GraphData.builder(this)
            .setPointMap(pointMap)
            .setGraphStroke(R.color.Black)
            .animateLine(true)
            .setPointRadius(10)
            .build()

        val pointMap2 = PointMap()
        pointMap2.addPoint(0, 500)
        pointMap2.addPoint(2, 400)
        pointMap2.addPoint(4, 600)
        pointMap2.addPoint(5, 800)
        val gd2 = GraphData.builder(this)
            .setPointMap(pointMap2)
            .setGraphStroke(R.color.Black)
            .animateLine(true)
            .setPointRadius(10)
            .build()

//        Handler().postDelayed({
//            curveGraphView.setData(6, 1000, gd, gd2)
//        }, 250)
        button.setOnClickListener {
            curveGraphView.setData(6, 1000, gd, gd2)
        }

        GlobalScope.launch(Dispatchers.Main) {

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> drawerlayout.openDrawer(GravityCompat.START, true)
        }
        return true
    }
}