package com.example.neocafeteae1prototype.view_model.regisration_vm

import androidx.lifecycle.ViewModel
import com.example.neocafeteae1prototype.repository.MainRepository
import com.example.neocafeteae1prototype.view.tools.logging
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(private val repository: MainRepository):ViewModel() {

    var userCreated = false

    fun sendUserData(number:Int, uid:String, name:String, birthDay:String){
        CoroutineScope(Dispatchers.IO).launch{
            val request = repository.postUserData(number, uid, name, birthDay)
            if (request.isSuccessful){
                "isSuccessful".logging()
                if (request.body() == "User is created") userCreated = true
            }
        }

    }

}