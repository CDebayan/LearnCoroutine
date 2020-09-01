package com.dc.learncoroutine.networkcall.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.dc.learncoroutine.networkcall.model.EmployeeModel
import com.dc.learncoroutine.networkcall.respository.NetworkCallRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlin.system.measureTimeMillis

class SequentialNetworkCallViewModel(application: Application) : AndroidViewModel(application) {


    fun fetchUser(): LiveData<Resource> = liveData(IO) {
        emit(Resource.Loading)
        val time = measureTimeMillis {
            val response1 = NetworkCallRepository.employeeDetails("1")
            val response2 = NetworkCallRepository.employeeDetails("2")
            if (response1.status == "success" && response2.status == "success") {
                emit(Resource.Success(response1.data, response2.data))
            } else {
                if (response1.status != "success") {
                    emit(Resource.Error(response1.status, response1.message))
                } else if (response2.status != "success") {
                    emit(Resource.Error(response2.status, response2.message))
                }
            }
        }
        Log.d("coroutineTest", time.toString())
    }

    sealed class Resource {
        data class Success(val data: EmployeeModel? = null, val data1: EmployeeModel? = null) :
            Resource()

        data class Error(val status: String, val message: String? = null) : Resource()
        object Loading : Resource()
    }
}