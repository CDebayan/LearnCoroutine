package com.dc.learncoroutine.viewmodelscope.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ViewModelScopeViewModel(application: Application) : AndroidViewModel(application) {
    private var count: Int = 0
    private val _response: MutableLiveData<String> = MutableLiveData()
    val response: LiveData<String> get() = _response

    /* We are creating a viewModelScope in IO thread. For that fakeApiCall() operation is done on IO thread.
     * viewModelScope automatically cancels the job when viewmodel is destroyed
     */
    fun fetchFakeApi() {
        viewModelScope.launch(IO) {
            val res = fakeApiCall()
            Log.d("coroutineTest", Thread.currentThread().name + res)
            _response.postValue(res)
        }
    }

    private suspend fun fakeApiCall(): String {
        delay(3000)
        Log.d("coroutineTest", Thread.currentThread().name + "api")
        return "Result ${count++}"
    }
}