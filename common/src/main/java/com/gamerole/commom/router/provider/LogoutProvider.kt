package com.gamerole.commom.router.provider

import com.alibaba.android.arouter.facade.template.IProvider

/**
 * 作者 :吕志豪
 * 简书：https://www.jianshu.com/u/6e525b929aac
 * github：https://github.com/lvzhihao100
 * 描述：
 * 创建时间：2018-09-20 21:50
 */
interface LogoutProvider : IProvider {
    fun logout()
    fun logout(msg: String?)
}