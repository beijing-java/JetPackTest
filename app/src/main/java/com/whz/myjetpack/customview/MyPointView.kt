package com.whz.myjetpack.customview

import android.content.Context
import android.graphics.*
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.util.AttributeSet
import android.view.View
import com.whz.myjetpack.R

/**
 * Created by 王鹏程 on 2020/5/20.
 * 类(MyPointView)解释:
 * 自定义PointView，配合ObjectAnimator使用
 * setPointRadius ->pointRadius 或者 PointRadius
 *
 */
class MyPointView(context: Context, attrs: AttributeSet) : View(context, attrs), Runnable {


    var mRadius = 50F
    lateinit var paint: Paint
    lateinit var bitmap: Bitmap
    lateinit var bitmapSrc: Bitmap
    var isClick = false
    lateinit var porterDuffXfermode: PorterDuffXfermode

    init {
        initPaint()
        initBitmap(context)
        initEffect()

    }

    private fun initEffect() {
        // ColorMatrixColorFilter 是ColorFilter的子类 颜色矩阵
        val colorMatrix = ColorMatrix(
            floatArrayOf(
                0F, 1F, 0F, 0F, 0F,
                0F, 0f, 1F, 0F, 0F,
                0F, 0F, 0f, 1F, 0F,
                0F, 0F, 0F, 1F, 0F
            )
        )
        // 颜色矩阵对于图片来说，调节更加明显
//        paint.colorFilter = ColorMatrixColorFilter(colorMatrix)
        // 色彩亮度调节
//        val lightingMatrix = LightingColorFilter(0xFFFFFFFF.toInt(), 0x00FF0000)
        // 图片叠加
//        val porterDuffColorFilter=PorterDuffColorFilter(Color.RED,PorterDuff.Mode.LIGHTEN)
//        setOnClickListener {
//            if (isClick) {
//                paint.colorFilter = porterDuffColorFilter
//                isClick=false
//            } else {
//                paint.colorFilter = null
//                isClick=true
//            }
//
//            postInvalidate()
//        }
        //叠加模式
        porterDuffXfermode = PorterDuffXfermode(PorterDuff.Mode.DST)


    }

    private fun initPaint() {
        // 不建议在draw/layout中创建对象，防止频繁绘制
        // 画笔具有所有画的属性，而画布具有要画的东西
        paint = Paint(ANTI_ALIAS_FLAG)
        paint.color = Color.BLUE
        paint.style = Paint.Style.FILL_AND_STROKE
        paint.strokeWidth = 10F
//        paint.alpha = 150
    }

    fun initBitmap(context: Context) {
        bitmap = BitmapFactory.decodeResource(context.resources, R.mipmap.belle1)//目标图
        bitmapSrc = BitmapFactory.decodeResource(context.resources, R.mipmap.belle2)
    }

    override fun onDraw(canvas: Canvas?) {

//        canvas!!.drawCircle(300F, 300F, mRadius, paint)
        val sc = canvas!!.saveLayer(0F, 0F, width.toFloat(), height.toFloat(), null, Canvas.ALL_SAVE_FLAG)

        canvas!!.drawBitmap(bitmap, 300F, 300F, paint)
        paint.setXfermode(porterDuffXfermode)
        canvas!!.drawBitmap(bitmapSrc, 300F, 300F, paint)

        paint.setXfermode(null)
        canvas!!.restoreToCount(sc)
        super.onDraw(canvas)
    }

    /**
     * 这里设置对应属性，方便ObjectAnimator调用
     */
    fun setPointRadius(radius: Float) {
        mRadius = radius
        invalidate()
    }

    /**
     * 这里对应ObjectAnimator获取对应的初始值，如果没有get方法，则初始值为0
     */
    fun getPointRadius() {

    }

    override fun run() {
        for (radius in 50..200 step 10) {
            if (mRadius < radius) {
                mRadius = radius.toFloat()
                postInvalidate()
            }
            Thread.sleep(40)
        }
    }
}