package com.example.neocafeteae1prototype.view.bottom_navigation_items.home.home_search

import androidx.lifecycle.ViewModel
import com.example.neocafeteae1prototype.R
import com.example.neocafeteae1prototype.data.models.AllModels

class SearchViewModel: ViewModel() {
    val list = mutableListOf<AllModels.Popular>(
        AllModels.Popular("Пончики", "90c", R.drawable.ponchiki, false, 0, "Выпечка"),
        AllModels.Popular("Апельсиновый сок", "50c", R.drawable.juice, false, 0, "Напитки"),
        AllModels.Popular("Чесночный багет с базиликом", "150c", R.drawable.bucket, false,0, "Выпечка")
    )
}