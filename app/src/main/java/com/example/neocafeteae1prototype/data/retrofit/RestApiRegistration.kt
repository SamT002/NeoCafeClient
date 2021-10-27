package com.example.neocafeteae1prototype.data.retrofit

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
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
}