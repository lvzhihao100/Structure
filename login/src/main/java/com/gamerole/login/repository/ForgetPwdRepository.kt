package com.gamerole.login.repository

import androidx.annotation.WorkerThread
import com.gamerole.commom.entity.HttpData
import com.gamerole.commom.http.Resource
import com.gamerole.login.service.HttpService
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ForgetPwdRepository @Inject constructor(private var httpService: HttpService) {

    @WorkerThread
    suspend fun sendCode(
        mobile: String,
        event: String
    ) = flow {
        httpService.sendCode(mobile, event)
            .suspendOnSuccess {
                println(data)
                var value = Resource.Success<HttpData<String>>(data)
                println(value)
                emit(value)
            }
            .suspendOnError { emit(Resource.Error<HttpData<String>>(this)) }
            .suspendOnException { emit(Resource.Error<HttpData<String>>(this)) }
            .suspendOnFailure { emit(Resource.Error<HttpData<String>>(null)) }
    }.flowOn(Dispatchers.IO)

    @WorkerThread
    suspend fun resetPwd(
        mobile: String,
        captcha: String,
        newpassword: String,
    ) = flow {
        httpService.resetPwd(mobile, captcha, newpassword)
            .suspendOnSuccess {
                println(data)
                var value = Resource.Success<HttpData<String>>(data)
                println(value)
                emit(value)
            }
            .suspendOnError { emit(Resource.Error<HttpData<String>>(this)) }
            .suspendOnException { emit(Resource.Error<HttpData<String>>(this)) }
            .suspendOnFailure { emit(Resource.Error<HttpData<String>>(null)) }
    }.flowOn(Dispatchers.IO)
}