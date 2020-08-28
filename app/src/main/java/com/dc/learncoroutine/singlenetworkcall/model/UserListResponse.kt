package com.dc.learncoroutine.singlenetworkcall.model

data class UserListResponse(
    val status: Int,
    val userList: List<User>? = null,
    val message: String? = null,
)