package com.whz.myjetpack.customview

import android.content.Context
import android.graphics.*
import android.graphics.Canvas.ALL_SAVE_FLAG
import android.util.AttributeSet
import android.view.View
import com.whz.myjetpack.R

/**
 * Created by 王鹏程 on 2020/5/12.
 * 类(PorterDuffXfermodeView)解释:
 *  理解Porter，Duff两者之间的叠加 2D图像合成
 */
class PorterDuffXfermodeView(context: Context) : View(context) {
    private val mPaint: Paint
    private val dstBmp: Bitmap
    private val srcBmp: Bitmap
    private val mXfermode: Xfermode
    val mPorterDuffMode = PorterDuff.Mode.MULTIPLY
    private lateinit var dstRect: RectF
    private lateinit var srcRect: RectF


    init { // 构造函数中初始化
        //设置去锯齿和图像过滤
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.FILTER_BITMAP_FLAG)
        dstBmp = BitmapFactory.decodeResource(resources, R.mipmap.destination)
        srcBmp = BitmapFactory.decodeResource(resources, R.mipmap.source)
        mXfermode = PorterDuffXfermode(mPorterDuffMode)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            it.drawColor(Color.WHITE)
            // 将绘制操作保存到新的图层，因为图像合成是很昂贵的操作，将用到硬件加速，这里将图像合成的处理放到离屏缓存中进行
            val saveCount = it.saveLayer(srcRect, mPaint, ALL_SAVE_FLAG)
            //绘制目标图
            it.drawBitmap(dstBmp, null, dstRect, mPaint)
            //设置混合模式
            mPaint.setXfermode(mXfermode)
            //绘制源图
            it.drawBitmap(srcBmp, null, srcRect, mPaint)
            //清除混合模式
            mPaint.setXfermode(null)
            //还原画布
            it.restoreToCount(saveCount)


        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
//        val width = if (w <= h) {
//            w
//        } else {
//            h
//        }
        //(a <= b) ? a : b 取最小的值
        val width = Math.min(w, h)
        val centerX = w / 2
        val centerY = h / 2
        val quarterWidth = width / 4.toFloat()
        srcRect = RectF(centerX - quarterWidth, centerY - quarterWidth, centerX + quarterWidth, centerY + quarterWidth)
        dstRect = RectF(centerX - quarterWidth, centerY - quarterWidth, centerX + quarterWidth, centerY + quarterWidth)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }
}
