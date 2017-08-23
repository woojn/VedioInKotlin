package cn.woojn.vedioinkotlin.ui

import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.ScaleAnimation
import cn.woojn.vedioinkotlin.R
import cn.woojn.vedioinkotlin.enterMainActivity
import kotlinx.android.synthetic.main.welcome_activity.*

/**
 * Created by wujun on 17-8-22.
 */
class WelComeActivity:AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //全屏
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.welcome_activity)
        init()
        set()
    }

    fun init(){
        //字体
        val font : Typeface = Typeface.createFromAsset(this.assets,"fonts/Lobster-1.4.otf")
        tv_name_english.typeface = font
        tv_english_intro.typeface = font
    }

    fun set(){
        //动画
        var alphaAnimation = AlphaAnimation(0.1f,1.0f)
        alphaAnimation.duration = 1000
        var scaleAnimation = ScaleAnimation(0.1f,1.0f,0.1f,1.0f,ScaleAnimation.RELATIVE_TO_SELF,0.5f,ScaleAnimation.RELATIVE_TO_SELF,0.5f)
        scaleAnimation.duration = 1000
        var animationSet = AnimationSet(true)
        animationSet.addAnimation(alphaAnimation)
        animationSet.addAnimation(scaleAnimation)
        animationSet.duration = 1000
        iv_icon_splash.startAnimation(animationSet)
        animationSet.setAnimationListener(object :Animation.AnimationListener{
            override fun onAnimationRepeat(p0: Animation?) {
            }

            override fun onAnimationEnd(p0: Animation?) {
                //TODO()跳转
//                newIntent<MainActivity>()
                enterMainActivity(this@WelComeActivity)
                finish()
            }

            override fun onAnimationStart(p0: Animation?) {
            }

        })
    }
}