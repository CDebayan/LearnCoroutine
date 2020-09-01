package com.dc.learncoroutine.networkcall.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dc.learncoroutine.networkcall.model.EmployeeModel
import com.dc.learncoroutine.networkcall.respository.NetworkCallRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

class ParallelNetworkCallViewModel(application: Application) : AndroidViewModel(application) {
    private val _response: MutableLiveData<Resource> = MutableLiveData()
    val response: LiveData<Resource> get() = _response

    fun fetchUser() {
        _response.postValue(Resource.Loading)
        viewModelScope.launch(IO) {
            val time = measureTimeMillis {
                val deferred1 = async { NetworkCallRepository.employeeDetails("1") }
                val deferred2 = async { NetworkCallRepository.employeeDetails("2") }

                val response1 = deferred1.await()
                val response2 = deferred2.await()

                if (response1.status == "success" && response2.status == "success") {
                    _response.postValue(Resource.Success(response1.data, response2.data))
                } else {
                    if (response1.status != "success") {
                        _response.postValue(Resource.Error(response1.status, response1.message))
                    } else if (response2.status != "success") {
                        _response.postValue(Resource.Error(response2.status, response2.message))
                    }
                }
            }
            Log.d("coroutineTest",time.toString())
        }
    }

    sealed class Resource {
        data class Success(val data: EmployeeModel? = null, val data1: EmployeeModel? = null) :
            Resource()

        data class Error(val status: String, val message: String? = null) : Resource()
        object Loading : Resource()
    }
}