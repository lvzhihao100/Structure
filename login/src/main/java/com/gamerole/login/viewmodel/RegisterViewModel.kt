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
import com.gamerole.login.entity.SubjectBean
import com.gamerole.login.repository.RegisterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RegisterViewModel @ViewModelInject constructor(private var registerRepository: RegisterRepository) :
    BaseViewModel() {

    private var _registerLiveData: MutableLiveData<HttpData<UserBean>> = MutableLiveData()
    val registerLiveData: LiveData<HttpData<UserBean>> get() = _registerLiveData

    fun register(account: String,pwd:String,code:String,subject_id:String) {
        viewModelScope.launch {
            object : NetworkAdapter<HttpData<UserBean>>(this@RegisterViewModel) {
                override suspend fun fetchFromRemote(): Flow<Resource<HttpData<UserBean>>> =
                    registerRepository.register(account,pwd,code,subject_id)
            }.asFlow()
                .collect {
                    _registerLiveData.postValue(it)
                }

        }
    }

    private var _sendCodeLiveData: MutableLiveData<HttpData<String>> = MutableLiveData()
    val sendCodeLiveData: LiveData<HttpData<String>> get() = _sendCodeLiveData

    fun sendCode(account: String) {
        viewModelScope.launch {
            object : NetworkAdapter<HttpData<String>>(this@RegisterViewModel) {
                override suspend fun fetchFromRemote(): Flow<Resource<HttpData<String>>> =
                    registerRepository.sendCode(account,"register")
            }.asFlow()
                .collect {
                    _sendCodeLiveData.postValue(it)
                }

        }
    }

    private var _subjectLiveData: MutableLiveData<HttpData<List<SubjectBean>>> = MutableLiveData()
    val subjectLiveData: LiveData<HttpData<List<SubjectBean>>> get() = _subjectLiveData
    fun subject() {
        viewModelScope.launch {
            object : NetworkAdapter<HttpData<List<SubjectBean>>>(this@RegisterViewModel) {
                override suspend fun fetchFromRemote(): Flow<Resource<HttpData<List<SubjectBean>>>> =
                    registerRepository.subject()
            }.asFlow(false)
                .collect {
                    _subjectLiveData.postValue(it)
                }

        }
    }
}