package com.dc.learncoroutine.utils

sealed class Resource<out T : Any>{
    data class Success<out T : Any>(val data: T? = null) : Resource<T>()
    data class Error<out T : Any>(val status : String,val message : String? = null) : Resource<T>()
    object Loading : Resource<Nothing>()
}
