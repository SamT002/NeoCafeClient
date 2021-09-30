package com.example.neocafeteae1prototype.application.bottom_navigation_items.home.HomeFragment

import androidx.lifecycle.ViewModel
import com.example.neocafeteae1prototype.R
import com.example.neocafeteae1prototype.domain.sealedClasses.AllModels

class HomeViewModel:ViewModel() {

    val list = mutableListOf<AllModels.Popular>(
        AllModels.Popular("Пончики", "90c", R.drawable.ponchiki, false, 0),
        AllModels.Popular("Апельсиновый сок", "50c", R.drawable.juice, false, 0),
        AllModels.Popular("Чесночный багет с базиликом", "150c", R.drawable.bucket, false,0)
    )

     val menuList = mutableListOf<AllModels.Menu>(
            AllModels.Menu("Чай", R.drawable.tea),
            AllModels.Menu("Кофе", R.drawable.coffee),
            AllModels.Menu("Напитки", R.drawable.soda),
            AllModels.Menu("Десерты", R.drawable.desert),
            AllModels.Menu("Выпечка", R.drawable.cake),
    )
}