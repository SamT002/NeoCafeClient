package com.example.neocafeteae1prototype.data.retrofit

import com.example.neocafeteae1prototype.data.models.AllModels
import retrofit2.Response
import retrofit2.http.*

interface UserAPI {

    @GET("user/")
    suspend fun getUserData(@Header("Authorization") token: String?): AllModels.User

    @GET("bonus/")
    suspend fun getBonus(@Header("Authorization") token:String):Response<Int>


    @FormUrlEncoded
    @PATCH("userch/")
    suspend fun changeName(@Header("Authorization") token:String,
    @Field("first_name") first_name:String)
}