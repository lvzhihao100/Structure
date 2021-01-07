package com.gamerole.commom.base

import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.gamerole.commom.R
import com.gamerole.commom.base.BaseViewModel
import com.gyf.immersionbar.ImmersionBar
import retrofit2.http.GET

abstract class BindingActivity : AppCompatActivity() {
    protected open fun getImmersion() = false
    abstract fun getViewModel(): BaseViewModel

    protected fun binding(
        @LayoutRes layoutId: Int
    ): Lazy<View> = lazy {
        setContentView(layoutId)
        val viewRoot = findViewById<View>(android.R.id.content)

        if (getImmersion()) {
            ImmersionBar.with(this)
                .fitsSystemWindows(false)  //使用该属性必须指定状态栏的颜色，不然状态栏透明，很难看
                .init();
        } else {
            ImmersionBar.with(this)
                .fitsSystemWindows(true)
                .statusBarColor(R.color.commonWhite)
                .statusBarDarkFont(true)
                .init()
        }
        viewRoot
    }

}
