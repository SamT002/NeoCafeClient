package com.example.neocafeteae1prototype.application.bottom_navigation_items.home.HomeSearchFragment

import androidx.lifecycle.ViewModel
import com.example.neocafeteae1prototype.R
import com.example.neocafeteae1prototype.domain.sealedClasses.AllModels

class SearchViewModel: ViewModel() {
    val list = mutableListOf<AllModels.Popular>(
        AllModels.Popular("Пончики", "90c", R.drawable.ponchiki, false, 0),
        AllModels.Popular("Апельсиновый сок", "50c", R.drawable.juice, false, 0),
        AllModels.Popular("Чесночный багет с базиликом", "150c", R.drawable.bucket, false,0)
    )
}