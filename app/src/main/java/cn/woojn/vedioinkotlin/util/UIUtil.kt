package cn.woojn.vedioinkotlin.util

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager

/**
 * Created by wujun on 17-8-22.
 */
class UIUtil{

    fun dip2px(context:Context,dip:Int): Int {
        val density = context.resources.displayMetrics.density
        return (density* dip + 0.5f).toInt()

    }

    fun px2dip(context:Context,px:Int): Int {
        val density = context.resources.displayMetrics.density
        return (density/px + 0.5f).toInt()

    }

    fun getScreenWidth(context: Context):Int{
        val wm = context
                .getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val outMetrics = DisplayMetrics()
        wm.defaultDisplay.getMetrics(outMetrics)
        return outMetrics.widthPixels
    }

    fun getScreenHeight(context: Context):Int{
        val wm = context
                .getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val outMetrics = DisplayMetrics()
        wm.defaultDisplay.getMetrics(outMetrics)
        return outMetrics.heightPixels
    }
}