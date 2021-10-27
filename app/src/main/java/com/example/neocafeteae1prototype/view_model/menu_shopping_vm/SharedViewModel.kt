package com.example.neocafeteae1prototype.view_model.menu_shopping_vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.neocafeteae1prototype.R
import com.example.neocafeteae1prototype.data.models.AllModels
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor() : ViewModel() {

    private var sortedList = mutableListOf<AllModels.Popular>()
    val bonus = 50
    fun getList(): MutableLiveData<List<AllModels.Popular>> {
        return list
    }
    val shoppingList = mutableListOf<AllModels.Popular>()

    val menuList = mutableListOf<AllModels.Menu>(
        AllModels.Menu("Чай", R.drawable.tea),
        AllModels.Menu("Кофе", R.drawable.coffee),
        AllModels.Menu("Напитки", R.drawable.soda),
        AllModels.Menu("Десерты", R.drawable.desert),
        AllModels.Menu("Выпечка", R.drawable.cake),
    )

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



    fun sortProductForShopping(list: MutableList<AllModels.Popular>){
        shoppingList.clear()
        CoroutineScope(Dispatchers.Default).launch {
            for(i in list){
                if (i.county > 0){
                    shoppingList.add(i)
                }
            }
        }
    }


}