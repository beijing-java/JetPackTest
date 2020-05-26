package com.whz.myjetpack.customview

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.GradientDrawable.*
import android.graphics.drawable.RippleDrawable
import android.graphics.drawable.StateListDrawable
import android.os.Build
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import androidx.appcompat.widget.AppCompatButton
import com.whz.myjetpack.R

/**
 * Created by 王鹏程 on 2020/5/9.
 * 类(CommonShapeButton)解释:
 * https://github.com/xiaomingmuzi/CommonShapeButton/blob/master/app/src/main/java/com/lixm/commonshapebutton/view/CommonShapeButtonKotlin.kt
 * 手敲源码
 *
 * JvmOverloads注解修饰时，java调用kotlin会暴露多个重载方法。
 */
class CommonShapeButton @JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : AppCompatButton(
    context,
    attrs,
    defStyleAttr
) {

    init {
        val array = context.obtainStyledAttributes(attrs, R.styleable.CommonShapeButton)
        with(array) {
            mShapeMode = getInt(R.styleable.CommonShapeButton_csb_shapeMode, 0)
            mFillColor = getColor(R.styleable.CommonShapeButton_csb_fillColor, 0x00000000.toInt())
            mPressedColor = getColor(R.styleable.CommonShapeButton_csb_pressedColor, 0xFF666666.toInt())
            mStrokeColor = getColor(R.styleable.CommonShapeButton_csb_strokeColor, 0)
            mStrokeWidth = getDimensionPixelSize(R.styleable.CommonShapeButton_csb_strokeWidth, 0)
            mCornerRadius = getDimensionPixelSize(R.styleable.CommonShapeButton_csb_cornerRadius, 0)
            mCornerPosition = getInt(R.styleable.CommonShapeButton_csb_drawablePosition, -1)
            mActiveEnable = getBoolean(R.styleable.CommonShapeButton_csb_activeEnable, false)
            mDrawablePosition = getInt(R.styleable.CommonShapeButton_csb_drawablePosition, -1)
            mStartColor = getColor(R.styleable.CommonShapeButton_csb_startColor, 0xFFFFFFFF.toInt())
            mEndColor = getColor(R.styleable.CommonShapeButton_csb_endColor, 0xFFFFFFFF.toInt())
            mOrientation = getColor(R.styleable.CommonShapeButton_csb_orientation, 0)
            mTextIsFit = getBoolean(R.styleable.CommonShapeButton_csb_textIsFit, false)
            recycle()
        }
    }


    private companion object {
        val TOP_LEFT = 1
        val TOP_RIGHT = 2
        val BOTTOM_RIGHT = 4
        val BOTTOM_LEFT = 8
    }

    /**
     * 普通shape样式
     */
    private val normalGradientDrawable: GradientDrawable by lazy { GradientDrawable() }
    /**
     * 按压shape样式
     */
    private val pressedGradientDrawable: GradientDrawable by lazy { GradientDrawable() }

    /**
     * shape样式集合
     */
    private val stateListDrawable: StateListDrawable by lazy { StateListDrawable() }

    private var mStartColor = 0 // 起始颜色
    private var mEndColor = 0 // 结束颜色
    private var mFillColor = 0 //填充颜色
    private var mStrokeColor = 0 //描边颜色
    private var mPressedColor = 0 // 按压的颜色

    private var mActiveEnable = false //是否开启点击效果

    /**
     * 渐变的方向
     * 0 -> TOP_BOTTOM
     * 1 -> LEFT_RIGHT
     */
    private var mOrientation = 0


    /**
     * shape模式
     * 矩形（rectangle） 椭圆形（oval）线性（line）环形（ring）
     */
    private var mShapeMode = 0

    /**
     *  圆角半径
     */
    private var mCornerRadius = 0

    /**
     * 圆角位置
     */
    private var mCornerPosition = 0


    private var mStrokeWidth = 0
    // -1 没有设置 0左边 1 上边 2 右边 3 下边 设置drawableLeft
    private var mDrawablePosition = -1;

    private var contentWidth = 0f
    private var contentHeight = 0f

    private var mTextIsFit = false;

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        with(normalGradientDrawable) {
            //渐变色
            if (mStartColor != 0xFFFFFFFF.toInt() && mEndColor != 0xFFFFFFFF.toInt()) {
                // setColors
                colors = kotlin.intArrayOf(mStartColor, mEndColor)
                // 直接设置给GradientDrawable 的setOrientation
                orientation = when (mOrientation) {
                    0 -> Orientation.TOP_BOTTOM
                    1 -> Orientation.LEFT_RIGHT
                    else -> Orientation.TOP_BOTTOM
                }
            } else {//填充色
                setColor(mFillColor)
            }
            // setShape 设置形状
            shape = when (mShapeMode) {
                0 -> RECTANGLE
                1 -> OVAL
                2 -> LINE
                3 -> RING
                else -> RECTANGLE
            }
            // 设置圆角半径
            if (mCornerPosition == -1) {
                cornerRadius = mCornerRadius.toFloat()
            } else {//根据圆角位置设置圆角半径
                cornerRadii = getCornerRadiusByPosition()
            }
            // 默认的透明边框不绘制，否则会导致没有阴影
            if (mStrokeColor != 0 && mStrokeWidth > 0) {
                setStroke(mStrokeWidth, mStrokeColor)
            }
        }
        // 是否开启点击动效
        background = if (mActiveEnable) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                RippleDrawable(ColorStateList.valueOf(mPressedColor), normalGradientDrawable, null)
            } else {
                with(pressedGradientDrawable) {
                    setColor(mPressedColor)
                    shape = when (mShapeMode) {
                        0 -> GradientDrawable.RECTANGLE
                        1 -> GradientDrawable.OVAL
                        2 -> GradientDrawable.LINE
                        3 -> GradientDrawable.RING
                        else -> RECTANGLE
                    }
                    cornerRadius = mCornerRadius.toFloat()
                    setStroke(mStrokeWidth, mStrokeColor)
                }
                //设置pressed状态
                stateListDrawable.apply {
                    addState(intArrayOf(android.R.attr.state_pressed), pressedGradientDrawable)
                    //设置normal状态
                    addState(intArrayOf(), normalGradientDrawable)
                }

            }
        } else {
            normalGradientDrawable
        }

        compoundDrawables

    }

    override fun layout(l: Int, t: Int, r: Int, b: Int) {
        super.layout(l, t, r, b)
        if (mDrawablePosition > -1) {
            compoundDrawables?.let {
                val drawable = it[mDrawablePosition]
                drawable?.let {
                    when (mDrawablePosition) {
                        0, 2 -> {
                            val drawableWidth = it.intrinsicWidth
                            val textWidth = paint.measureText(text.toString())//测量文字的宽度
                            contentWidth = drawableWidth + textWidth + compoundDrawablePadding
                            val rightPadding = (width - contentWidth).toInt()

                            // 图片跟文字全部靠左侧
                            setPadding(0, 0, rightPadding, 0)

                        }
                        1, 3 -> {
                            val drawableHeight = it.intrinsicHeight
                            val fontMetrics = paint.fontMetrics// 获取文字高度
                            val singeLineHeight =
                                Math.ceil((fontMetrics.descent - fontMetrics.ascent).toDouble()).toFloat()
                            val totalLineSpaceHeight = (lineCount - 1) * lineSpacingExtra // 计算出总的行间距
                            val textHeight = singeLineHeight * lineCount + totalLineSpaceHeight
                            contentHeight = textHeight + drawableHeight + compoundDrawablePadding
                            val bottomPadding = (height - contentHeight).toInt()
                            setPadding(0, 0, 0, bottomPadding)

                        }
                    }
                }

            }
        }
        gravity = Gravity.CENTER
        isClickable = true


    }

    override fun onDraw(canvas: Canvas?) {

        when {
            contentWidth > 0 && (mDrawablePosition == 0 || mDrawablePosition == 2) -> {
                canvas!!.translate((width - contentWidth) / 2.toFloat(), 0f)
            }
            contentHeight > 0 && (mDrawablePosition == 1 || mDrawablePosition == 3) -> {
                canvas!!.translate(0f, (height - contentHeight) / 2.toFloat())
            }
        }
        super.onDraw(canvas)
        refitText(text.toString(), width)
    }

    private lateinit var mTextPaint: Paint
    private var mTextSize = 0.toFloat()
    private fun refitText(texts: String, width: Int) {
        if (!mTextIsFit || texts == null || width <= 0) {
            return;
        }
        mTextPaint = Paint()

        mTextPaint.set(paint)
        val availableTextViewWidth = width - paddingLeft - paddingRight
        val charsWidthArr = FloatArray(texts.length)
        val boundsRect = Rect()
        mTextPaint.getTextBounds(texts, 0, texts.length, boundsRect)
        var textWidth = boundsRect.width()
        mTextSize = textSize
        while (textWidth > availableTextViewWidth) {
            mTextSize -= 1f
            mTextPaint.textSize = mTextSize
            textWidth = mTextPaint.getTextWidths(texts, charsWidthArr)

        }
        setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize)

    }


//https://github.com/michaelxs/Android-CommonShapeButton/blob/master/app/src/main/java/com/blue/view/CommonShapeButton.kt
    /**
     * 根据圆角坐标获取圆角半径
     */
    private fun getCornerRadiusByPosition(): FloatArray {
        val result = floatArrayOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f)
        val cornerRadius = mCornerRadius.toFloat()
        if (containsFlag(mCornerPosition, TOP_LEFT)) {
            result[0] = cornerRadius
            result[1] = cornerRadius
        }
        if (containsFlag(mCornerPosition, TOP_RIGHT)) {
            result[2] = cornerRadius
            result[3] = cornerRadius
        }
        if (containsFlag(mCornerPosition, BOTTOM_LEFT)) {
            result[4] = cornerRadius
            result[5] = cornerRadius
        }
        if (containsFlag(mCornerPosition, BOTTOM_RIGHT)) {
            result[6] = cornerRadius
            result[7] = cornerRadius
        }
        return result
    }

    //TODO 这里不是很清楚---位运算符
    private fun containsFlag(flagSet: Int, flag: Int): Boolean {
        return flagSet or flag == flagSet
    }
}