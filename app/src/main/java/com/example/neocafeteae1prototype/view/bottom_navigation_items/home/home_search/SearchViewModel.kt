package com.example.neocafeteae1prototype.view.bottom_navigation_items.home.home_search

import androidx.lifecycle.ViewModel
import com.example.neocafeteae1prototype.R
import com.example.neocafeteae1prototype.data.models.AllModels

class SearchViewModel: ViewModel() {
    val list = mutableListOf<AllModels.Popular>()
}