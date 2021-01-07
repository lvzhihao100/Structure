package com.gamerole.login.service

import com.gamerole.commom.entity.HttpData
import com.gamerole.commom.entity.UserBean
import com.gamerole.login.entity.SubjectBean
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface HttpService {

    /**
     *
     */
    @POST("api/user/login")
    @FormUrlEncoded
    suspend fun login(
        @Field("account") account: String,
        @Field("password") pwd: String
    ): ApiResponse<HttpData<UserBean>>

    @POST("api/user/register")
    @FormUrlEncoded
    suspend fun register(
        @Field("mobile") mobile: String,
        @Field("password") password: String,
        @Field("code") code: String,
        @Field("subject_id") subject_id: String,
    ): ApiResponse<HttpData<UserBean>>

    @GET("api/subject/index")
    suspend fun subject(

    ): ApiResponse<HttpData<List<SubjectBean>>>

    @POST("api/sms/send")
    @FormUrlEncoded
    suspend fun sendCode(
        @Field("mobile") mobile: String,
        @Field("event") event: String,
    ): ApiResponse<HttpData<String>>

    @POST("api/user/resetpwd")
    @FormUrlEncoded
    suspend fun resetPwd(
        @Field("mobile") mobile: String,
        @Field("captcha") captcha: String,
        @Field("newpassword") newpassword: String,
    ): ApiResponse<HttpData<String>>

}