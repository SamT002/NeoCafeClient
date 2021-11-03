package com.example.neocafeteae1prototype.view_model.qr_vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.neocafeteae1prototype.data.models.AllModels
import com.example.neocafeteae1prototype.data.models.Resource
import com.example.neocafeteae1prototype.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QRViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {

    val table = MutableLiveData<Resource<AllModels.Table>>()
    val lockedTable = MutableLiveData<AllModels.Table>()

    fun checkTable(table:String, token:String){
        viewModelScope.launch {
            val response = async { repository.checkTable(table, token) }.await()
            this@QRViewModel.table.postValue(response)
            }
        }

    fun lockTable(table: String, token: String){
        viewModelScope.launch {
            val response = async { repository.lockTable(table, token) }.await()
            if (response is Resource.Success)
            this@QRViewModel.lockedTable.postValue(response.value)
        }
    }



    }

