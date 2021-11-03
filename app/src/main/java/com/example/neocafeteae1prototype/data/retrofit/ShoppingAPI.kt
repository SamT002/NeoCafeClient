package com.example.neocafeteae1prototype.data.retrofit

import com.example.neocafeteae1prototype.data.models.AllModels
import retrofit2.http.GET

interface ShoppingAPI {

    @GET("ncafe/products/")
    suspend fun getAllProduct(): AllModels.Test

}