package com.example.neocafeteae1prototype.view_model.user_vm


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.neocafeteae1prototype.data.models.AllModels
import com.example.neocafeteae1prototype.data.models.Resource
import com.example.neocafeteae1prototype.repository.MainRepository
import com.example.neocafeteae1prototype.view.tools.mainLogging
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {

    val userData = MutableLiveData<Resource<AllModels.User>>()
    var bonus = 0

    fun changeUserName(token:String, name:String){
        viewModelScope.launch {
            repository.changeUserName(token, name)
        }
    }


    fun getUserInfo(token:String){
        "UserViewModel".mainLogging()

        CoroutineScope(Dispatchers.IO).launch {
            val response = async { repository.getUserInfo(token) }.await()

            if (response is Resource.Success){
                userData.postValue(Resource.Success(response.value))
            }
        }

        CoroutineScope(Dispatchers.IO).launch {
            val bonusResponse = repository.getBonus(token)
            if (bonusResponse.isSuccessful){
                bonus = bonusResponse.body() ?: 0
            }
        }
    }




}