package com.dc.learncoroutine.networkcall.model


import android.graphics.drawable.Drawable
import coil.request.ImageRequest
import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("avatar")
    val avatar: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    val drawable: Drawable? = null
)