package com.example.neocafeteae1prototype.repository

import com.example.neocafeteae1prototype.data.retrofit.RestApiRegistration
import retrofit2.Response
import javax.inject.Inject

class MainRepository @Inject constructor(private val registration:RestApiRegistration) {

    suspend fun postUserData(number:Int, password:String, name:String, birthDate:String): Response<String>{
       return registration.sendUserData(number, password, name, birthDate)
    }

}