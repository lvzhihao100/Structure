package com.gamerole.login.ui

import androidx.activity.viewModels
import androidx.lifecycle.observe
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.gamerole.commom.DataStoreConfig
import com.gamerole.commom.RoutePath
import com.gamerole.commom.base.BaseActivity
import com.gamerole.commom.box.verifier.ToastNextInputs
import com.gamerole.commom.entity.HttpData
import com.gamerole.commom.entity.UserBean
import com.gamerole.commom.extention.SchemeUtil
import com.gamerole.commom.extention.click
import com.gamerole.commom.util.DataStoreUtils
import com.gamerole.commom.util.EditUtil
import com.gamerole.login.databinding.LoginActivityLoginBinding
import com.gamerole.login.viewmodel.LoginViewModel
import com.hi.dhl.binding.viewbind
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
@Route(path = RoutePath.LOGIN_LOGIN)
class LoginActivity : BaseActivity() {
    override fun getImmersion() = true

    private val loginViewModel: LoginViewModel by viewModels()
    override fun getViewModel() = loginViewModel
    private val binding: LoginActivityLoginBinding by viewbind()

    override fun initView() {
        with(binding) {
            clIncludeHead.tvTitle.text = "登录"
            clIncludeHead.tvRight.text = "注册"
            clIncludeHead.tvRight.click {
                ARouter.getInstance().build(RoutePath.LOGIN_REGISTER).navigation()
            }
            val input = ToastNextInputs()
            input
                .add(etAccount, SchemeUtil.notEmpty(etAccount))
                .add(llPwd.etPwd, SchemeUtil.notEmpty(llPwd.etPwd))
            btLogin.setOnClickListener {
                if (input.test()) {
                    loginViewModel.login(etAccount.text.toString(), llPwd.etPwd.text.toString())
                }
            }
            EditUtil.togglePs(llPwd.etPwd, llPwd.ivPwdToggle)
            tvPwdForget.click {
                ARouter.getInstance().build(RoutePath.LOGIN_FORGET_PWD)
                    .navigation()
            }
        }
//        GlobalScope.launch {
//            DataStoreUtils.putData(DataStoreConfig.TOKEN, "132123")
//        }
    }


    override fun initData() {
        loginViewModel.loginLiveData
            .observe(this, { httpData: HttpData<UserBean> -> loginSuccess(httpData.data!!) })
    }

    private fun loginSuccess(userBean: UserBean) {
        GlobalScope.launch {
            DataStoreUtils.putData(DataStoreConfig.TOKEN, userBean.token)
        }
        ARouter.getInstance().build(RoutePath.APP_MAIN)
            .navigation()
        finish()
    }
}