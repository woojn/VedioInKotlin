package cn.woojn.vedioinkotlin.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import cn.woojn.vedioinkotlin.R
import cn.woojn.vedioinkotlin.util.setDefaultColor

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setDefaultColor(this)
    }
}
