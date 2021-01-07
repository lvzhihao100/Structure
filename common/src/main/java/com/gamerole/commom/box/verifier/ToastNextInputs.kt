package com.gamerole.commom.box.verifier

import com.gamerole.commom.box.androidinput.ToastMessageDisplay
import com.github.yoojia.inputs.AndroidNextInputs
class ToastNextInputs : AndroidNextInputs() {
    init {
        setMessageDisplay(ToastMessageDisplay())
    }
}