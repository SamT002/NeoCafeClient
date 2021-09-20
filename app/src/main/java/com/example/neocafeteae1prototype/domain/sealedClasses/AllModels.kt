package com.example.neocafeteae1prototype.domain.sealedClasses

import android.os.Parcelable
import java.io.Serializable

sealed class AllModels: Serializable{

    data class Popular(val name:String, val price:String, val image: Int, val star:Boolean): AllModels()

    data class Menu(val name:String, val image: Int): AllModels()

    data class NeoCafePlace(val image: String, val street:String, val time:String ): AllModels()
}
