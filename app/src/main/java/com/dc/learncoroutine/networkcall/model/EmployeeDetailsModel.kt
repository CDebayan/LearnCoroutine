package com.dc.learncoroutine.networkcall.model


import com.google.gson.annotations.SerializedName

data class EmployeeDetailsModel(
    @SerializedName("data")
    val data: EmployeeModel? = null,
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("status")
    val status: String
)