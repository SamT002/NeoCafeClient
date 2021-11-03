package com.example.neocafeteae1prototype.view_model.branches_vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
class BranchViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {

    val listOfBranches = MutableLiveData<Resource<AllModels.Branches>>()

    init {
        getAllBranches()
    }

    private fun getAllBranches(){
            CoroutineScope(Dispatchers.IO).launch {
                val response = async { repository.getAllBranches() }.await()
                if (response is Resource.Success){
                    listOfBranches.postValue(Resource.Success(response.value))
                }else{
                    Resource.Failure(false, null, null)
                }
            }
        }
}