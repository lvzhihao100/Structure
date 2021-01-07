package com.gamerole.login.repository

import androidx.annotation.WorkerThread
import com.gamerole.commom.entity.HttpData
import com.gamerole.commom.entity.UserBean
import com.gamerole.commom.http.Resource
import com.gamerole.login.entity.SubjectBean
import com.gamerole.login.service.HttpService
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RegisterRepository @Inject constructor(private var httpService: HttpService) {
    @WorkerThread
    suspend fun register(
         mobile: String,
         password: String,
         code:String,
         subject_id:String
    ) = flow {
        httpService.register(mobile, password, code,subject_id)
            .suspendOnSuccess {

                println(data)
                emit(Resource.Success<HttpData<UserBean>>(data))
            }
            .suspendOnError { emit(Resource.Error<HttpData<UserBean>>(this)) }
            .suspendOnException { emit(Resource.Error<HttpData<UserBean>>(this)) }
            .suspendOnFailure { emit(Resource.Error<HttpData<UserBean>>(null)) }
    }.flowOn(Dispatchers.IO)

    @WorkerThread
    suspend fun sendCode(
        mobile: String,
        event:String
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
    suspend fun subject(

    ) = flow {
        httpService.subject()
            .suspendOnSuccess {
                println(data)
                var value = Resource.Success<HttpData<List<SubjectBean>>>(data)
                println(value)
                emit(value)
            }
            .suspendOnError { emit(Resource.Error<HttpData<List<SubjectBean>>>(this)) }
            .suspendOnException { emit(Resource.Error<HttpData<List<SubjectBean>>>(this)) }
            .suspendOnFailure { emit(Resource.Error<HttpData<List<SubjectBean>>>(null)) }
    }.flowOn(Dispatchers.IO)
}