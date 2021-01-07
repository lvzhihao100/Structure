package com.gamerole.commom.base

import androidx.fragment.app.Fragment

abstract class BindingFragment constructor(contentLayoutId : Int): Fragment(contentLayoutId) {

    abstract fun getViewModel(): BaseViewModel

}
