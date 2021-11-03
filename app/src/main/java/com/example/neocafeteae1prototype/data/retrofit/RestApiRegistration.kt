package com.example.neocafeteae1prototype.data.retrofit

import com.example.neocafeteae1prototype.data.models.AllModels
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface RestApiRegistration {

    @FormUrlEncoded
    @POST("register/")
    suspend fun sendUserData(
        @Field("number") number: Int,
        @Field("password") password: String,
        @Field("first_name") name: String,
        @Field("birthDate") birthDate:String
    ): Response<String>

    @FormUrlEncoded
    @POST("token/")
    suspend fun getJWTtoken(
        @Field("number") number: Int,
        @Field("password") uid:String
    ):Response<AllModels.JWT_token>

    @FormUrlEncoded
    @GET("authorize/")
    suspend fun checkUserNumber(@Field("number") number: Int):Response<Boolean>

}