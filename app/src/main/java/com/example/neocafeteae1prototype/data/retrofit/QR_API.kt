package com.example.neocafeteae1prototype.data.retrofit

import com.example.neocafeteae1prototype.data.models.AllModels
import retrofit2.Response
import retrofit2.http.*

interface QR_API {

    @GET("ncafe/tables/{filial1table1}/")
    suspend fun checkTable(@Header("Authorization") token:String,
        @Path(value = "filial1table1", encoded = true) table:String): AllModels.Table

    @POST("ncafe/tables/{filial1table1}/")
    suspend fun lockTable(@Header("Authorization") token: String,
    @Path(value = "filial1table1", encoded = true) table: String): AllModels.Table
}

