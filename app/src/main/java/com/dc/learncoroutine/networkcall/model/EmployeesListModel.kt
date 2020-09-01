package com.dc.learncoroutine.networkcall.model


import com.google.gson.annotations.SerializedName

data class EmployeesListModel(
    @SerializedName("data")
    val employeesList: List<EmployeeModel>? = null,
    @SerializedName("status")
    val status: String,
    val message: String? = null,
)