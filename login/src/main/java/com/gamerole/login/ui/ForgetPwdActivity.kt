package com.gamerole.login.ui

import androidx.activity.viewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.gamerole.commom.RoutePath
import com.gamerole.commom.base.BaseActivity
import com.gamerole.commom.box.verifier.ToastNextInputs
import com.gamerole.commom.extention.SchemeUtil
import com.gamerole.commom.extention.click
import com.gamerole.commom.extention.sendCode
import com.gamerole.commom.util.EditUtil
import com.gamerole.login.databinding.LoginActivityForgetPwdBinding
import com.gamerole.login.viewmodel.ForgetPwdViewModel
import com.github.yoojia.inputs.TextLazyLoader
import com.hi.dhl.binding.viewbind
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@Route(path = RoutePath.LOGIN_FORGET_PWD)
class ForgetPwdActivity : BaseActivity() {
    override fun getImmersion() = true

    private val viewModelForgetPwd: ForgetPwdViewModel by viewModels()
    override fun getViewModel() = viewModelForgetPwd
    private val binding: LoginActivityForgetPwdBinding by viewbind()

    override fun initView() {
        with(binding) {
            clIncludeHead.tvTitle.text = "忘记密码"
            clIncludeHead.tvRight.text = "登录"
            clIncludeHead.tvRight.click {
                ARouter.getInstance().build(RoutePath.LOGIN_LOGIN).navigation()
            }
            val input = ToastNextInputs()
            input
                .add(etAccount, SchemeUtil.notEmpty(etAccount))
                .add(etCode, SchemeUtil.notEmpty(etCode))
                .add(etPwd, SchemeUtil.notEmpty(etPwd),SchemeUtil.pwd())
                .add(etPwdAgain, SchemeUtil.notEmpty(etPwdAgain),SchemeUtil.equalsPwd(TextLazyLoader(etPwd)))
            btSubmit.click {
                if (input.test()) {
                    viewModelForgetPwd.resetPwd(
                        etAccount.text.toString(),
                        etCode.text.toString(),
                        etPwd.text.toString()
                    )
                }
            }
            val inputCheckCode = ToastNextInputs()
            inputCheckCode
                .add(etAccount, SchemeUtil.notEmpty(etAccount))
            btCheckCode.click {
                if (inputCheckCode.test()) {
                    viewModelForgetPwd.sendCode(etAccount.text.toString())
                }
            }
            EditUtil.togglePs(etPwd,ivPwdToggle)
            EditUtil.togglePs(etPwdAgain,ivPwdToggleAgain)
            EditUtil.togglePs(etPwdAgain,ivPwdToggleAgain)
        }
    }

    override fun initData() {
        viewModelForgetPwd.sendCodeLiveData.observe(this) {
            sendCode(binding.btCheckCode)
        }
        viewModelForgetPwd.resetPwdLiveData.observe(this) {
            ARouter.getInstance().build(RoutePath.LOGIN_LOGIN).navigation()
            finish()
        }
    }
}