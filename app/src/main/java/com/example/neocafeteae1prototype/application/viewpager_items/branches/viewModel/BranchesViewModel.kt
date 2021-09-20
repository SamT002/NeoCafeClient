package com.example.neocafeteae1prototype.application.viewpager_items.branches.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.neocafeteae1prototype.domain.sealedClasses.AllModels

class BranchesViewModel : ViewModel() {

    var model:AllModels.NeoCafePlace? = null




    val list = mutableListOf<AllModels>(
        AllModels.NeoCafePlace("https://www.screenly.io/assets/coffee-shop-digital-sign-c1a802afd67812d4f641854893f2ea8b94ade4c9df445cac471c4f8f27208e94.png", "Шопокова 15б", "9:00 - 22:00"),
        AllModels.NeoCafePlace("https://i.pinimg.com/originals/7a/10/20/7a1020befab0387e9ee19d95d4647cd6.jpg", "Горького 4", "15:00 - 22:00"),
        AllModels.NeoCafePlace("https://cdn2.howtostartanllc.com/images/business-ideas/business-idea-images/Coffee-Shop.jpg", "Анкара 29", "8:00 - 23:00"))


    fun setItem(item: AllModels.NeoCafePlace) {
        Log.i("TAG", item.street)
        model = item
    }

    fun getData():AllModels.NeoCafePlace{
        return model!!
    }




}