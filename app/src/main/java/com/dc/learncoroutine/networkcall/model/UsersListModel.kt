package com.dc.learncoroutine.networkcall.model


data class UsersListModel(
    val status: String,
    val message: String? = null,
    val userList: List<UserModel>? = null
)