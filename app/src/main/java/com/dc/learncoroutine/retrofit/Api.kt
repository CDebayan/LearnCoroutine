package com.dc.learncoroutine.retrofit

import com.dc.learncoroutine.singlenetworkcall.model.User
import retrofit2.http.GET


interface Api {
    @GET("users")
    suspend fun usersList(): List<User>
}