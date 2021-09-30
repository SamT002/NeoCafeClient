package com.example.neocafeteae1prototype.application.bottom_navigation_items.user.user_shopping_history

import androidx.lifecycle.ViewModel
import com.example.neocafeteae1prototype.domain.sealedClasses.AllModels

class UserShoppingViewModel : ViewModel() {

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

    val list = listOf<AllModels.Receipt>(
            AllModels.Receipt(listProduct , false, "Tuesday", "Kulatov st", "500"),
            AllModels.Receipt(listProduct, true, "Monday", "Kulatov st", "500"),
            AllModels.Receipt(listProduct, true, "Friday", "Soviet", "500"),
            AllModels.Receipt(listProduct,  false, "18:15", "Panfilov st", "500"),
    )

}