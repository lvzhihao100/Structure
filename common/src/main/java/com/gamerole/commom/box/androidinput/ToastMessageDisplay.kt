package com.gamerole.commom.box.androidinput

import com.gamerole.commom.util.ToastUtil
import com.github.yoojia.inputs.Input
import com.github.yoojia.inputs.MessageDisplay

class ToastMessageDisplay : MessageDisplay {
    override fun show(input: Input, message: String) {
        ToastUtil.showShort(message)
    }
}