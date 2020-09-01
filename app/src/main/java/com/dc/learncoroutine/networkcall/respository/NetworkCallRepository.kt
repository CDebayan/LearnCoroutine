package com.dc.learncoroutine.networkcall.respository

import com.dc.learncoroutine.networkcall.model.EmployeeDetailsModel
import com.dc.learncoroutine.networkcall.model.EmployeesListModel
import com.dc.learncoroutine.retrofit.RetrofitClient
import com.dc.learncoroutine.utils.checkConnectivityError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object NetworkCallRepository {
    suspend fun employeesList(): EmployeesListModel {
        var response: EmployeesListModel
        withContext(Dispatchers.IO) {
            response = try {
                RetrofitClient.invoke().employeesList()
            } catch (e: Exception) {
                val error = checkConnectivityError(e)
                EmployeesListModel(status = error.status, message = error.message)
            }
        }
        return response
    }

    suspend fun employeeDetails(id: String): EmployeeDetailsModel {
        var response: EmployeeDetailsModel
        withContext(Dispatchers.IO) {
            response = try {
                RetrofitClient.invoke().employeeDetails(id)
            } catch (e: Exception) {
                val error = checkConnectivityError(e)
                EmployeeDetailsModel(status = error.status, message = error.message)
            }
        }
        return response
    }
}