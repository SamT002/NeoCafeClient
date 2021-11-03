package com.example.neocafeteae1prototype.view_model.regisration_vm


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.neocafeteae1prototype.data.models.AllModels
import com.example.neocafeteae1prototype.repository.MainRepository
import com.example.neocafeteae1prototype.view.tools.logging
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(private val repository: MainRepository):ViewModel() {

    var userCreated = MutableLiveData<Boolean>()
    var tokens = AllModels.JWT_token("null", "null")
    var isNumberLocateInDB = MutableLiveData<Boolean>()


    fun sendUserData(number:Int, uid:String, name:String, birthDay:String){
        CoroutineScope(Dispatchers.IO).launch{
            val request = repository.postUserData(number, uid, name, birthDay)
            if (request.isSuccessful){
                "isSuccessful".logging()
                if (request.body().equals("User is created")){
                    userCreated.postValue(true)
                }
            }
        }
    }

    fun checkNumber(number:Int){
        viewModelScope.launch {
            val request = repository.checkNumber(number)
            if(request.isSuccessful){
                isNumberLocateInDB.postValue(true)
            }
        }
    }

    fun JWTtoken(number:Int, uid:String){
        CoroutineScope(Dispatchers.IO).launch {
            val request = repository.getJWTtoken(number, uid)
            if (request.isSuccessful){
                tokens = request.body()!!
            }else
                return@launch
        }
    }


}