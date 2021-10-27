package com.example.neocafeteae1prototype.data.models

import java.io.Serializable

sealed class AllModels: Serializable{

    data class Popular(val name:String, val price:String, val image: Int, val star:Boolean,
                       var county: Int,val category: String): AllModels()

    data class Menu(val name:String, val image: Int): AllModels()

    data class NeoCafePlace(val image: String, val street:String, val time:String, val gis:String, val list: List<BranchWorkTime>, val numberPhone:String, val branchInfo:String, val facebookLink:String, val instagramLink:String ): AllModels()

    data class BranchWorkTime(val day:String, val startTime:String, val endTime:String, val work:Boolean): AllModels()

    data class Product(val productName:String, val productPrice:String, val county:String, val totalProductPrice:String):
        AllModels()

    data class Receipt(val list: List<Product>, val afterTenMin:Boolean, val time:String, val street:String, val total:String):
        AllModels()

    data class Notification(val title:String, val message:String, val time:String): AllModels()

}
