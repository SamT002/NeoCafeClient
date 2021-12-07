package com.example.neocafeteae1prototype.view_model.menu_shopping_vm

<<<<<<< HEAD

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.neocafeteae1prototype.data.models.AllModels
import com.example.neocafeteae1prototype.repository.MainRepository
import com.example.neocafeteae1prototype.view.root.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
=======
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.neocafeteae1prototype.data.models.AllModels
import com.example.neocafeteae1prototype.data.models.Resource
import com.example.neocafeteae1prototype.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
>>>>>>> 3ca4717 (Connected Shopping Fragment and connect QR Fragment)
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
<<<<<<< HEAD
class ShoppingOrderViewModel @Inject constructor(private val repository: MainRepository) : BaseViewModel() {

    val productList = mutableListOf<AllModels.Product>()
    val isProductListSent = MutableLiveData<Boolean>()
    override var errorLiveData: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
=======
class ShoppingOrderViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {

    val productList = mutableListOf<AllModels.Product>()
    val responseChecker = MutableLiveData<Resource<String>>()
>>>>>>> 3ca4717 (Connected Shopping Fragment and connect QR Fragment)

    fun getProductList(list: MutableList<AllModels.Popular>){
        list.map {
            productList.add(AllModels.Product(it.title, it.price.toString(), it.county.toString(), getTotalPrice(it.county, it.price),it.id ))
        }
    }

    private fun getTotalPrice(county: Int, price: Int): String {
        return "${county*price}"
    }

    fun sendProductList(tableId: String?, bonus: Int, token:String) {
        val list = mutableListOf<AllModels.FinishProduct>()
        val order = AllModels.Order(1, bonus)
        productList.map {
            list.add(AllModels.FinishProduct(it.id, it.county.toInt()))
        }

<<<<<<< HEAD
        viewModelScope.launch {
            repository.sendProductList(order, list as List<AllModels.FinishProduct>).let {
              /*  when(it){
                    is Resource.Failure -> errorLiveData.postValue(true)
                    is Resource.Success -> isProductListSent.postValue(true)
                }*/
            }
=======
        CoroutineScope(Dispatchers.IO).launch {
            val response = async { repository.sendProductList(order, list as List<AllModels.FinishProduct>, token) }.await()
            responseChecker.postValue(response)
>>>>>>> 3ca4717 (Connected Shopping Fragment and connect QR Fragment)
        }
    }
}