package cn.woojn.vedioinkotlin.ui

import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import cn.woojn.vedioinkotlin.R
import cn.woojn.vedioinkotlin.ui.fragment.FindFragment
import cn.woojn.vedioinkotlin.ui.fragment.HomeFragment
import cn.woojn.vedioinkotlin.ui.fragment.HotFragment
import cn.woojn.vedioinkotlin.ui.fragment.MineFragment
import cn.woojn.vedioinkotlin.util.setDefaultColor
import cn.woojn.vedioinkotlin.util.showToast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    var exitTime: Long = 0
    var toast: Toast? = null
    var homeFragment: HomeFragment? = null
    var hotFragment: HotFragment? = null
    var findFragment: FindFragment? = null
    var mineFragment: MineFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setDefaultColor(this)
        setRadioButton()
        initToolBar()
        initFragment(savedInstanceState)
    }

    private fun setRadioButton() {
        rb_home.isChecked = true//默认选中
        rb_home.setTextColor(resources.getColor(R.color.black))
        rb_home.setOnClickListener(this)
        rb_find.setOnClickListener(this)
        rb_hot.setOnClickListener(this)
        rb_mine.setOnClickListener(this)

    }

    private fun initToolBar() {
        var today = getDay()
        tv_bar_title.text = today
        tv_bar_title.typeface = Typeface.createFromAsset(this.assets, "fonts/Lobster-1.4.otf")
        iv_search.setOnClickListener {
            if (rb_mine.isChecked) {

            } else {
                //点击骚索
            }

        }
    }

    private fun getDay(): String {
        var list = arrayOf("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")
        var date: Date = Date()
        var calendar: Calendar = Calendar.getInstance()
        calendar.time = date
        var index: Int = calendar.get(Calendar.DAY_OF_WEEK) - 1
        if (index == -1)
            index = 0
        return list[index]
    }

    override fun onClick(v: View?) {
        clearState()
        when (v?.id) {
            R.id.rb_home -> {
                rb_home.isChecked = true
                rb_home.setTextColor(resources.getColor(R.color.black))
                iv_search.setBackgroundResource(R.drawable.icon_search)
                tv_bar_title.text = getDay()
                tv_bar_title.visibility = View.VISIBLE
                supportFragmentManager.beginTransaction().show(homeFragment)
                        .hide(findFragment).hide(hotFragment).hide(mineFragment).commit()
            }
            R.id.rb_find -> {
                rb_find.isChecked = true
                rb_find.setTextColor(resources.getColor(R.color.black))
                tv_bar_title.text = "Discover"
                tv_bar_title.visibility = View.VISIBLE
                iv_search.setBackgroundResource(R.drawable.icon_search)
                supportFragmentManager.beginTransaction().show(findFragment)
                        .hide(homeFragment).hide(hotFragment).hide(mineFragment).commit()
            }
            R.id.rb_hot -> {
                rb_hot.isChecked = true
                rb_hot.setTextColor(resources.getColor(R.color.black))
                tv_bar_title.text = "Ranking"
                tv_bar_title.visibility = View.VISIBLE
                iv_search.setBackgroundResource(R.drawable.icon_search)
                supportFragmentManager.beginTransaction().show(hotFragment)
                        .hide(findFragment).hide(homeFragment).hide(mineFragment).commit()
            }
            R.id.rb_mine -> {
                rb_mine.isChecked = true
                rb_mine.setTextColor(resources.getColor(R.color.black))
                tv_bar_title.visibility = View.INVISIBLE
                iv_search.setBackgroundResource(R.drawable.icon_setting)
                supportFragmentManager.beginTransaction().show(mineFragment)
                        .hide(findFragment).hide(hotFragment).hide(homeFragment).commit()
            }
        }
    }

    private fun initFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            var fragments: List<Fragment> = supportFragmentManager.fragments
            for (item in fragments) {
                if (item is HomeFragment) {
                    homeFragment = item
                }
                if (item is FindFragment) {
                    findFragment = item
                }
                if (item is HotFragment) {
                    hotFragment = item
                }
                if (item is MineFragment) {
                    mineFragment = item
                }
            }

        } else {
            homeFragment = HomeFragment()
            findFragment = FindFragment()
            hotFragment = HotFragment()
            mineFragment = MineFragment()

            val beginTransaction = supportFragmentManager.beginTransaction()
            beginTransaction.add(R.id.fl_content, homeFragment)
            beginTransaction.add(R.id.fl_content, findFragment)
            beginTransaction.add(R.id.fl_content, hotFragment)
            beginTransaction.add(R.id.fl_content, mineFragment)
            beginTransaction.commit()
        }

        supportFragmentManager.beginTransaction().show(homeFragment)
                                                .hide(findFragment)
                                                .hide(hotFragment)
                                                .hide(mineFragment)
                                                .commit()
    }

    private fun clearState() {
        rg_root.clearCheck()
        rb_home.setTextColor(resources.getColor(R.color.gray))
        rb_find.setTextColor(resources.getColor(R.color.gray))
        rb_hot.setTextColor(resources.getColor(R.color.gray))
        rb_mine.setTextColor(resources.getColor(R.color.gray))
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis().minus(exitTime) <= 3000) {
                finish()
                toast!!.cancel()
            } else {
                toast = showToast("再按一次退出")
                exitTime = System.currentTimeMillis()
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }


}


