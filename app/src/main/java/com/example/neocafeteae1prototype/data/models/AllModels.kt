package com.example.neocafeteae1prototype.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

sealed class AllModels: Serializable{

    data class Branches(val filials: List<Filial>, val instagram: String, val facebook: String)

    data class Table(
        val filialId: Int,
        val id: Int,
        val qrCode: String,
        val status: Boolean
    )

    data class Test(@SerializedName ("products")val products:MutableList<AllModels.Popular>)

    data class Filial(
        val adress: String,
        val description: String,
        val id: Int,
        val image: String,
        val link2Gis: String,
        val phone: String,
        val schedule: List<Schedule>
    ) : AllModels()

    data class Schedule(val day: String, val end: String, val isToday: Boolean, val start: String) : AllModels()

    data class SocialMedia(val instagram:String , val facebook:String) : Serializable

    data class User(val number:Int, val first_name:String, val birthDate:String)

    data class Popular(val category_name: String,
                       val description: String,
                       val image: String,
                       val isPopular: Boolean,
                       val measure: String,
                       val price: Int,
                       val title: String,
                       val volume: Int):AllModels()

//    data class Popular(val name:String, val price:String, val image: Int, val star:Boolean,
//                       var county: Int,val category: String): AllModels()

    data class Menu(val name:String, val image: Int): AllModels()

    data class Product(val productName:String, val productPrice:String, val county:String, val totalProductPrice:String):
        AllModels()

    data class Receipt(val list: List<Product>, val afterTenMin:Boolean, val time:String, val street:String, val total:String):
        AllModels()

    data class Notification(val title:String, val message:String, val time:String): AllModels()

    data class JWT_token(val refresh:String, val access:String)

}
