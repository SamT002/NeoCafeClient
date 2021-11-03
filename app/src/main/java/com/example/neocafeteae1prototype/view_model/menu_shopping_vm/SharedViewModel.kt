package com.example.neocafeteae1prototype.view_model.menu_shopping_vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.neocafeteae1prototype.R
import com.example.neocafeteae1prototype.data.models.AllModels
import com.example.neocafeteae1prototype.data.models.Resource
import com.example.neocafeteae1prototype.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {

    private var sortedList = mutableListOf<AllModels.Popular>()
    val bonus = 50

//    fun getList(): MutableLiveData<List<AllModels.Popular>> {
//        return list
//    }
    val list = MutableLiveData<Resource<AllModels.Test>>()

    init {
        getAllProduct()
    }

    private fun getAllProduct() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = async { repository.getAllProduct() }.await()
            list.postValue(response)
        }


    }

    val shoppingList = mutableListOf<AllModels.Popular>()

    val menuList = mutableListOf<AllModels.Menu>(
        AllModels.Menu("Чай", R.drawable.tea),
        AllModels.Menu("Кофе", R.drawable.coffee),
        AllModels.Menu("Напитки", R.drawable.soda),
        AllModels.Menu("Десерты", R.drawable.desert),
        AllModels.Menu("Выпечка", R.drawable.cake),
    )

    fun sort(category: String, list: MutableList<AllModels.Popular>): MutableList<AllModels.Popular> {
        val myList = mutableListOf<AllModels.Popular>()

        list.map {
            if (it.category_name == category) {
                myList.add(it)
            }
        }

        sortedList = myList
        if (category == "Все") {
            return list
        }

        return sortedList
    }


//    private val listData = mutableListOf<AllModels.Popular>(
//        AllModels.Popular("Пончики", "90c", R.drawable.ponchiki, false, 0, "Выпечка"),
//        AllModels.Popular("Апельсиновый сок", "50c", R.drawable.juice, false, 0, "Напитки"),
//        AllModels.Popular("Чесночный багет с базиликом", "150c", R.drawable.bucket,
//            false,
//            0,
//            "Выпечка"
//        )
//    )

//    private val list: MutableLiveData<List<AllModels.Popular>> = MutableLiveData(listData)
//
//
//    fun sortProductForShopping(list: MutableList<AllModels.Popular>) {
//        shoppingList.clear()
//        viewModelScope.launch {
//            list.map {
//                if (it.county > 0) {
//                    shoppingList.add(it)
//                }
//            }
//        }
//    }
}