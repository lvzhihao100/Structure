package com.gamerole.login.repository

import androidx.annotation.WorkerThread
import com.gamerole.commom.entity.HttpData
import com.gamerole.commom.entity.UserBean
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

class LoginRepository @Inject constructor(private val httpService: HttpService) {
    @WorkerThread
    suspend fun login(
        account: String,
        pwd: String
    ) = flow {
        httpService.login(account, pwd)
            .suspendOnSuccess {
                println(data)
                emit(Resource.Success<HttpData<UserBean>>(data))
            }
            .suspendOnError { emit(Resource.Error<HttpData<UserBean>>(this)) }
            .suspendOnException { emit(Resource.Error<HttpData<UserBean>>(this)) }
            .suspendOnFailure { emit(Resource.Error<HttpData<UserBean>>(null)) }
    }.flowOn(Dispatchers.IO)

}