package com.example.neocafeteae1prototype.application.bottom_navigation_items

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.neocafeteae1prototype.R
import com.example.neocafeteae1prototype.domain.sealedClasses.AllModels
import kotlinx.coroutines.launch

class SharedViewModel : ViewModel() {

    private var sortedList = mutableListOf<AllModels.Popular>()
    val bonus = 50

    fun sort(category:String, list:MutableList<AllModels.Popular>):MutableList<AllModels.Popular>{
        val myList = mutableListOf<AllModels.Popular>()
        for (i in list){
            if (i.category == category){
                myList.add(i)
            }

        }
        sortedList = myList
        if(category == "Все"){
            return list
        }

        return sortedList
    }


    private val listData = mutableListOf<AllModels.Popular>(
        AllModels.Popular("Пончики", "90c", R.drawable.ponchiki, false, 0, "Выпечка"),
        AllModels.Popular("Апельсиновый сок", "50c", R.drawable.juice, false, 0, "Напитки"),
        AllModels.Popular("Чесночный багет с базиликом", "150c", R.drawable.bucket, false,0, "Выпечка")
    )

    private val list:MutableLiveData<List<AllModels.Popular>> = MutableLiveData(listData)

    fun getList(): MutableLiveData<List<AllModels.Popular>> {
        return list
    }

    private val shoppingList = mutableListOf<AllModels.Popular>()
    val shoppingLiveData = MutableLiveData<MutableList<AllModels.Popular>>()


    fun setShoppingList(element:AllModels.Popular){
        shoppingList.add(element)
        shoppingLiveData.value = shoppingList
    }


}