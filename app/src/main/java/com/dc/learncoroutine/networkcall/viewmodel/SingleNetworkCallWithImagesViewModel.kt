package com.dc.learncoroutine.networkcall.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.dc.learncoroutine.networkcall.model.UsersListModel
import com.dc.learncoroutine.networkcall.respository.NetworkCallRepository
import com.dc.learncoroutine.utils.Resource
import kotlinx.coroutines.Dispatchers

class SingleNetworkCallWithImagesViewModel(application: Application) :
    AndroidViewModel(application) {
    fun fetchUsersList(): LiveData<Resource<UsersListModel>> = liveData(Dispatchers.IO) {
        emit(Resource.Loading)
        val response = NetworkCallRepository.userList()
        if (response.status == "success") {
            emit(Resource.Success(response))
        } else {
            emit(Resource.Error<UsersListModel>(response.status, response.message))
        }
    }
}