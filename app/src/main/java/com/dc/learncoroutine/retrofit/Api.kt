package com.dc.learncoroutine.retrofit


import com.dc.learncoroutine.networkcall.model.EmployeeDetailsModel
import com.dc.learncoroutine.networkcall.model.EmployeesListModel
import retrofit2.http.GET
import retrofit2.http.Path


interface Api {
    @GET("employees")
    suspend fun employeesList(): EmployeesListModel

    @GET("employee/{id}")
    suspend fun employeeDetails(@Path("id") id : String): EmployeeDetailsModel
}