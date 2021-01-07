package com.gamerole.login.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gamerole.commom.base.BaseViewModel
import com.gamerole.commom.entity.HttpData
import com.gamerole.commom.entity.UserBean
import com.gamerole.commom.http.NetworkAdapter
import com.gamerole.commom.http.Resource
import com.gamerole.login.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LoginViewModel @ViewModelInject constructor(private val loginRepository: LoginRepository) :
    BaseViewModel() {
    private var _loginLiveData: MutableLiveData<HttpData<UserBean>> = MutableLiveData()
    val loginLiveData: LiveData<HttpData<UserBean>> get() = _loginLiveData

    fun login(account: String,pwd:String) {
        viewModelScope.launch {
            object : NetworkAdapter<HttpData<UserBean>>(this@LoginViewModel) {
                override suspend fun fetchFromRemote(): Flow<Resource<HttpData<UserBean>>> =
                    loginRepository.login(account,pwd)
            }.asFlow()
                .collect {
                    _loginLiveData.postValue(it)
                }

        }
    }
}