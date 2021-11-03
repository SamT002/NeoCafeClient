package com.example.neocafeteae1prototype.data.retrofit

import com.example.neocafeteae1prototype.data.models.AllModels
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface BranchAPI {

    @GET("ncafe/filials/")
    suspend fun getBranches(): AllModels.Branches

}