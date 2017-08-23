package cn.woojn.vedioinkotlin

import android.app.Activity
import android.content.Context
import android.content.Intent
import cn.woojn.vedioinkotlin.ui.MainActivity

/**
 * Created by wujun on 17-8-23.
 */
inline fun <reified T: Activity> Activity.newIntent(){
    var intent = Intent(this,T::class.java)
    this.startActivity(intent)
}

fun enterMainActivity(context: Context){
    var intent = Intent(context,MainActivity::class.java)
    context.startActivity(intent)
}