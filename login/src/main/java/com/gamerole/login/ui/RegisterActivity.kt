package com.gamerole.login.ui

import androidx.activity.viewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.gamerole.commom.DataStoreConfig
import com.gamerole.commom.RoutePath
import com.gamerole.commom.base.BaseActivity
import com.gamerole.commom.box.verifier.ToastNextInputs
import com.gamerole.commom.extention.SchemeUtil
import com.gamerole.commom.extention.click
import com.gamerole.commom.extention.sendCode
import com.gamerole.commom.util.DataStoreUtils
import com.gamerole.commom.util.EditUtil
import com.gamerole.login.databinding.LoginActivityRegisterBinding
import com.gamerole.login.viewmodel.RegisterViewModel
import com.github.yoojia.inputs.TextLazyLoader
import com.hi.dhl.binding.viewbind
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
@Route(path = RoutePath.LOGIN_REGISTER)
class RegisterActivity : BaseActivity() {
    override fun getImmersion() = true

    private val viewModelRegister: RegisterViewModel by viewModels()
    override fun getViewModel() = viewModelRegister
    private val binding: LoginActivityRegisterBinding by viewbind()
    private var subjectId = 0
    override fun initView() {
        with(binding) {
            clIncludeHead.tvTitle.text = "手机注册"
            clIncludeHead.tvRight.text = "登录"
            clIncludeHead.tvRight.click {
                ARouter.getInstance().build(RoutePath.LOGIN_LOGIN).navigation()
            }
            val input = ToastNextInputs()
            input
                .add(etAccount, SchemeUtil.notEmpty(etAccount))
                .add(etCode, SchemeUtil.notEmpty(etCode))
                .add(etPwd, SchemeUtil.notEmpty(etPwd), SchemeUtil.pwd())
                .add(
                    etPwdAgain,
                    SchemeUtil.notEmpty(etPwdAgain),
                    SchemeUtil.equalsPwd(TextLazyLoader(etPwd))
                )
            btRegister.click {
                if (input.test()) {
                    viewModelRegister.register(
                        etAccount.text.toString(),
                        etPwd.text.toString(),
                        etCode.text.toString(),
                        "$subjectId"
                    )
                }
            }
            val inputCheckCode = ToastNextInputs()
            inputCheckCode
                .add(etAccount, SchemeUtil.notEmpty(etAccount))
            btCheckCode.click {
                if (inputCheckCode.test()) {
                    viewModelRegister.sendCode(etAccount.text.toString())
                }
            }
            EditUtil.togglePs(etPwd, ivPwdToggle)
            EditUtil.togglePs(etPwdAgain, ivPwdToggleAgain)


        }
        viewModelRegister.subject()

    }

    override fun initData() {
        viewModelRegister.registerLiveData.observe(this) {
            GlobalScope.launch {
                DataStoreUtils.putData(DataStoreConfig.TOKEN, it.data?.token)
            }
            ARouter.getInstance().build(RoutePath.APP_MAIN)
                .navigation()
        }

        viewModelRegister.sendCodeLiveData.observe(this) {
            sendCode(binding.btCheckCode)
        }
        viewModelRegister.subjectLiveData.observe(this) {
            if (!it.data.isNullOrEmpty()){
                subjectId = it.data?.get(0)?.subject_id!!
                binding.apply {
                    heySpinner.attachData(it.data?.map { subjectBean -> subjectBean.name })
                    heySpinner.setOnSelectListener { position ->
                        subjectId = it.data?.get(position)?.subject_id!!
                    }
                }
            }

        }

    }
}