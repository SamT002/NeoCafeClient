package com.example.neocafeteae1prototype.repository

import com.example.neocafeteae1prototype.data.models.AllModels
import com.example.neocafeteae1prototype.data.models.Resource
import com.example.neocafeteae1prototype.view.tools.bearerToken
import com.example.neocafeteae1prototype.data.models.SafeApiCall
import com.example.neocafeteae1prototype.data.retrofit.*
import retrofit2.Response
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val registration: RestApiRegistration,
    private val branchAPI: BranchAPI,
    private val userAPI: UserAPI,
    private val qrApi: QR_API,
    private val shoppingAPI: ShoppingAPI
): SafeApiCall {

    suspend fun postUserData(number: Int, password: String, name: String, birthDate: String): Response<String> {
        return registration.sendUserData(number, password, name, birthDate)
    }

    suspend fun getJWTtoken(number: Int, uid: String): Response<AllModels.JWT_token> {
        return registration.getJWTtoken(number, uid)
    }

    suspend fun getAllBranches(): Resource<AllModels.Branches> {
        return safeApiCall { branchAPI.getBranches() }
    }

    suspend fun getUserInfo(token:String): Resource<AllModels.User>{
        return safeApiCall { userAPI.getUserData(token.bearerToken()) }
    }

    suspend fun getBonus(token:String):Response<Int>{
        return userAPI.getBonus(token.bearerToken())
    }

    suspend fun checkNumber(number: Int):Response<Boolean>{
        return registration.checkUserNumber(number)
    }

    suspend fun changeUserName(token:String,name:String){
        return userAPI.changeName(token.bearerToken(), name)
    }

    suspend fun checkTable(table:String, token:String):Resource<AllModels.Table>{
        return safeApiCall { qrApi.checkTable(token.bearerToken(), table) }
    }

    suspend fun lockTable(table:String, token:String):Resource<AllModels.Table>{
        return safeApiCall { qrApi.lockTable(token.bearerToken(), table) }
    }

    suspend fun getAllProduct():Resource<AllModels.Test>{
        return safeApiCall { shoppingAPI.getAllProduct() }
    }

}