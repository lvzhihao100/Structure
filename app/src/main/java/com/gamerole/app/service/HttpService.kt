package com.gamerole.app.service

import com.gamerole.commom.entity.HttpData
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface HttpService {

    @GET("login")
    suspend fun login(
        @Query("account") account: String,
        @Query("offset") pwd: String
    ): ApiResponse<HttpData<String>>


}