package com.dc.learncoroutine.singlenetworkcall.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.dc.learncoroutine.singlenetworkcall.model.User
import com.dc.learncoroutine.singlenetworkcall.repository.SingleNetworkCallRepository
import kotlinx.coroutines.Dispatchers.IO

class SingleNetworkCallViewModel(application: Application) : AndroidViewModel(application) {

    fun fetchUserList(): LiveData<State> = liveData(IO) {
        emit(State.Loading)
        val response = SingleNetworkCallRepository.userList()
        if (response.status == 1) {
            emit(State.Success(response.userList))
        } else {
            emit(State.Error(response.message))
        }
    }

    sealed class State {
        object Loading : State()
        data class Success(val userList: List<User>?) : State()
        data class Error(val message: String?) : State()
    }
}