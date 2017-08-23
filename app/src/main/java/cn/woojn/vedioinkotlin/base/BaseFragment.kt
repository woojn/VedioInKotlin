package cn.woojn.vedioinkotlin.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by wujun on 17-8-23.
 */
abstract class BaseFragment: Fragment(){
    var rootView:View? = null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if(rootView == null)
            rootView = inflater?.inflate(getResourceId(),container,false)
        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    abstract fun getResourceId():Int

    abstract fun initView()

}