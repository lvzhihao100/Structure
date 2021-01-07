package com.gamerole.login.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gamerole.commom.base.BaseViewModel
import com.gamerole.commom.entity.HttpData
import com.gamerole.commom.http.NetworkAdapter
import com.gamerole.commom.http.Resource
import com.gamerole.login.repository.ForgetPwdRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ForgetPwdViewModel @ViewModelInject constructor(
    private var forgetPwdRepository: ForgetPwdRepository
) :
    BaseViewModel() {

    private var _sendCodeLiveData: MutableLiveData<HttpData<String>> = MutableLiveData()
    val sendCodeLiveData: LiveData<HttpData<String>> get() = _sendCodeLiveData
    fun sendCode(account: String) {
        viewModelScope.launch {
            object : NetworkAdapter<HttpData<String>>(this@ForgetPwdViewModel) {
                override suspend fun fetchFromRemote(): Flow<Resource<HttpData<String>>> =
                    forgetPwdRepository.sendCode(account, "resetpwd")
            }.asFlow()
                .collect {
                    _sendCodeLiveData.postValue(it)
                }

        }
    }

    private var _resetPwdLiveData: MutableLiveData<HttpData<String>> = MutableLiveData()
    val resetPwdLiveData: LiveData<HttpData<String>> get() = _resetPwdLiveData
    fun resetPwd(
        mobile: String,
        captcha: String,
        newpassword: String,
    ) {
        viewModelScope.launch {
            object : NetworkAdapter<HttpData<String>>(this@ForgetPwdViewModel) {
                override suspend fun fetchFromRemote(): Flow<Resource<HttpData<String>>> =
                    forgetPwdRepository.resetPwd(mobile, captcha, newpassword)
            }.asFlow(false)
                .collect {
                    _resetPwdLiveData.postValue(it)
                }

        }
    }
}