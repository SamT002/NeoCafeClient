package com.example.neocafeteae1prototype.view_model.user_shopping_history_vm

import androidx.lifecycle.ViewModel
import com.example.neocafeteae1prototype.data.models.AllModels
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserShoppingViewModel @Inject constructor() : ViewModel() {

    private val listProduct = listOf<AllModels.Product>(
            AllModels.Product("Coffee", "120 c", "3", "360"),
            AllModels.Product("Coffee", "120 c", "3", "360"),
            AllModels.Product("Coffee", "120 c", "3", "360"),
            AllModels.Product("Coffee", "120 c", "3", "360"),
            AllModels.Product("Coffee", "120 c", "3", "360"),
            AllModels.Product("Coffee", "120 c", "3", "360"),
            AllModels.Product("Coffee", "120 c", "3", "360"),
            AllModels.Product("Coffee", "120 c", "3", "360"),
    )

    val list = mutableListOf<AllModels.Receipt>(
            AllModels.Receipt(listProduct , false, "Tuesday", "Kulatov st", "500"),
            AllModels.Receipt(listProduct, true, "Monday", "Kulatov st", "500"),
            AllModels.Receipt(listProduct, true, "Friday", "Soviet", "500"),
            AllModels.Receipt(listProduct,  false, "18:15", "Panfilov st", "500"),
    )

}