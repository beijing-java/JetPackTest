package com.whz.myjetpack.animation

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Build
import android.os.Bundle
import android.view.animation.*
import android.view.animation.Animation.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.addListener
import com.whz.myjetpack.R
import com.whz.myjetpack.utils.LogUtils
import kotlinx.android.synthetic.main.activity_animation.*
import java.util.*

/**
 * Created by 王鹏程 on 2020/5/19.
 * 类(AnimationActivity)解释:
 *
 */
class AnimationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)
//        setScaleAnimation()
//        setTranslateAnimation()
//        setRotateAnimation()
//        setValueAnimator()
        setObjectAnimator()
        Thread(act_animation_mypoint).start()
    }

    /**
     * 设置view的缩放
     */
    fun setScaleAnimation() {
//        val scaleAnimation=ScaleAnimation(0f,1.4f,0f,1.4f)
//        val scaleAnimation=ScaleAnimation(0f,1.4f,0f,1.4f,100f,100f)
        // RELATIVE_TO_SELF 相对于自己  RELATIVE_TO_PARENT 相对于父布局  ABSOLUTE 绝对值，固定值
//        val scaleAnimation=ScaleAnimation(0f,1.4f,0f,1.4f,RELATIVE_TO_SELF,0.5f,RELATIVE_TO_SELF,0.5f)
        val scaleAnimation = ScaleAnimation(0f, 1.4f, 0f, 1.4f, ABSOLUTE, 100f, ABSOLUTE, 500f)
        scaleAnimation.duration = 2000
        scaleAnimation.fillAfter = false
        scaleAnimation.fillBefore = false
        scaleAnimation.repeatCount = 2

        act_animation_iv.startAnimation(scaleAnimation)
    }

    /**
     * 设置view的平移
     */
    fun setTranslateAnimation() {
//        val translateAnimation=TranslateAnimation(0f,100f,0f,0f)
        // RELATIVE_TO_SELF 相对于自己  RELATIVE_TO_PARENT 相对于父布局  ABSOLUTE 绝对值，固定值(0.0-1.0  1.0=100%)
        val translateAnimation = TranslateAnimation(
            TranslateAnimation.RELATIVE_TO_SELF, 0f, TranslateAnimation.RELATIVE_TO_SELF, 0.5f,
            TranslateAnimation.RELATIVE_TO_SELF, 0f, TranslateAnimation.RELATIVE_TO_SELF, 0.5f
        );
        translateAnimation.duration = 2000
        translateAnimation.repeatCount = 2

        act_animation_iv.startAnimation(translateAnimation)
    }

    /**
     * 设置view的旋转
     */
    fun setRotateAnimation() {
//        val rotateAnimation= RotateAnimation(0f,180f,100f,100f)
        val rotateAnimation = RotateAnimation(
            0f, -720f, RotateAnimation.RELATIVE_TO_SELF, 0.5f,
            RotateAnimation.RELATIVE_TO_SELF, 0.5f
        );
        rotateAnimation.duration = 2000
        rotateAnimation.repeatCount = 2

        act_animation_iv.startAnimation(rotateAnimation)
    }

    /**
     * 设置valueAnimator 设计师
     */
    fun setValueAnimator() {
//        val valueAnimator = ValueAnimator.ofInt(0, 4000)
        val valueAnimator = ValueAnimator.ofFloat(0F, 5000F, 500F, 1000F)
        valueAnimator.interpolator = LinearInterpolator()
        valueAnimator.duration = 2000
        valueAnimator.addUpdateListener {
            val curValue = (it.animatedValue as Float).toInt() / 10
            act_animation_tv.layout(curValue, 0, curValue + act_animation_tv.width, act_animation_tv.height)

        }
        valueAnimator.repeatCount = INFINITE
        valueAnimator.repeatMode = ValueAnimator.REVERSE
        valueAnimator.start()
        valueAnimator.addListener(onEnd = { animator ->
            LogUtils.d("动画结束")

        })
    }

    /**
     * ObjectAnimator属性动画
     */
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun setObjectAnimator() {
        // alpha rotaion rotationX rotationY translationX translationY scaleX scaleY
//        val objectAnimator=ObjectAnimator.ofFloat(act_animation_iv,"ScaleY",0F,2F,1F)
// 使用自定义的set方法，setPointRadius
//        val objectAnimator=ObjectAnimator.ofFloat(act_animation_mypoint,"pointRadius",100F)
        val objectAnimator = ObjectAnimator.ofArgb(act_animation_iv, "backgroundColor", 0xffff00ff.toInt(), 0xffffff00.toInt())
        objectAnimator.interpolator = BounceInterpolator()
        objectAnimator.duration = 2000
        objectAnimator.repeatCount = 5
        objectAnimator.start()
    }
}