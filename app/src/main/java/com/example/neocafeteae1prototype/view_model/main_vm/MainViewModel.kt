package com.example.neocafeteae1prototype.view_model.main_vm


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.neocafeteae1prototype.data.models.AllModels
import com.example.neocafeteae1prototype.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {

    val list = MutableLiveData<AllModels.JWT_token>()

    fun JWTtoken(number:Int, uid:String){
        viewModelScope.launch {
            val request = repository.getJWTtoken(number, uid)
            if (request.isSuccessful){
                list.postValue(request.body())
            }
        }
    }

}



