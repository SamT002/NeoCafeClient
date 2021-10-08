package com.example.neocafeteae1prototype.application.bottom_navigation_items.home.popularProducts

import androidx.lifecycle.ViewModel
import com.example.neocafeteae1prototype.R
import com.example.neocafeteae1prototype.domain.sealedClasses.AllModels

class PopularViewModel : ViewModel() {

    val list = mutableListOf<AllModels.Popular>(
        AllModels.Popular("Пончики", "90c", R.drawable.ponchiki, false, 0, "Выпечка"),
        AllModels.Popular("Апельсиновый сок", "50c", R.drawable.juice, false, 0, "Напитки"),
        AllModels.Popular("Чесночный багет с базиликом", "150c", R.drawable.bucket, false,0, "Выпечка")
    )

}