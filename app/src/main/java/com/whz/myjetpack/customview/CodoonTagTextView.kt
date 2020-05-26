package com.whz.myjetpack.customview

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatTextView

/**
 * Created by 王鹏程 on 2020/5/9.
 * 类(CodoonTagTextView)解释:
 *
 */
class CodoonTagTextView(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    AppCompatTextView(context, attrs, defStyleAttr) {

    /**
     * 将view转化为bitmap
     */
    private fun convertViewToBitmap(view: View): Bitmap {
        view.measure(
            MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
            MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
        )
        view.layout(0, 0, view.measuredWidth, view.measuredHeight)
        val bitmap = Bitmap.createBitmap(view.measuredWidth, view.measuredHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        canvas.drawColor(Color.WHITE)
        view.draw(canvas)
        return bitmap
    }

    fun setTagText(style: Int, content:String){

    }

    companion object {
        const val STYLE_NONE = 0 //不加
        const val STYLE_JD = 1 //京东
        const val STYLE_TB = 2 //淘宝
        const val STYLE_CODOON = 3 //自营
        const val STYLE_PDD = 4 //拼多多
        const val STYLE_TM = 5 //天猫
    }
}