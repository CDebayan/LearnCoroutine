package com.dc.learncoroutine.singlenetworkcall.repository

import com.dc.learncoroutine.retrofit.RetrofitClient
import com.dc.learncoroutine.singlenetworkcall.model.UserListResponse
import com.dc.learncoroutine.utils.checkConnectivityError
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

object SingleNetworkCallRepository {

    suspend fun userList(): UserListResponse {
        var response: UserListResponse
        withContext(IO) {
            response = try {
                val userList = RetrofitClient.invoke().usersList()
                UserListResponse(status = 1, userList = userList)
            } catch (e: Exception) {
                val error = checkConnectivityError(e)
                UserListResponse(status = error.status, message = error.message)
            }
        }
        return response
    }
}