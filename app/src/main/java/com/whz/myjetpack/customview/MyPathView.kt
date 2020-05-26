package com.whz.myjetpack.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_DOWN
import android.view.MotionEvent.ACTION_MOVE
import android.view.View

/**
 * Created by 王鹏程 on 2020/5/20.
 * 类(MyPathView)解释:
 * 二阶贝塞尔曲线的练习学习
 * 所谓二阶贝塞尔，其实是两个一阶，以及控制点来进行控制
 */
class MyPathView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    val path = Path()
    //记录初始按下的值
    var mPreX = 0F
    var mPreY = 0F
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        // 实现手写连线功能
        when (event!!.action) {
            ACTION_DOWN -> {
                path.moveTo(event.x, event.y)
                mPreX=event.x
                mPreY=event.y
                return true
            }
            ACTION_MOVE -> {
                // 画线，没有优化弧度，直来直往
//                path.lineTo(event.x, event.y)
                // 优化添加弧度
                val endX=(mPreX+event.x)/2
                val endY=(mPreY+event.y)/2
                path.quadTo(mPreX,mPreY,endX,endY)
                mPreX=event.x
                mPreY=event.y

                // 使用postInvalidate方法，可以不用考虑线程的问题
                postInvalidate()
            }
        }

        return super.onTouchEvent(event)
    }

    override fun onDraw(canvas: Canvas?) {
        val paint = Paint()
        paint.style = Paint.Style.STROKE
        paint.color = Color.RED
        // 这里使用quadTo方法来进行二阶贝塞尔曲线画
//        path.moveTo(100F, 300F)//初始坐标值
//        path.quadTo(200F, 200F, 300F, 300F)
//        path.quadTo(300F, 100F, 500F, 200F)
        canvas!!.drawPath(path, paint)
        super.onDraw(canvas)
    }
}