package com.gamerole.commom.util

import com.gamerole.commom.base.App
import es.dmoral.toasty.Toasty

object ToastUtil {
    fun showShort(message: CharSequence?) {

        Toasty.normal(App.INSTANCE, message!!).show()
    }
}