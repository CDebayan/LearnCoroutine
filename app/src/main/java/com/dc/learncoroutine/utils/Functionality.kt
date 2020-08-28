package com.dc.learncoroutine.utils

import com.dc.learncoroutine.utils.model.GeneralError
import java.net.ConnectException
import java.net.SocketTimeoutException

fun checkConnectivityError(t: Throwable): GeneralError {
    return when (t) {
        is ConnectException -> {
            GeneralError(-1, "No Internet Connection")
        }
        is SocketTimeoutException -> {
            GeneralError(-2, "Cannot connect to server")
        }
        else -> {
            GeneralError(0, "Something went wrong")
        }
    }
}