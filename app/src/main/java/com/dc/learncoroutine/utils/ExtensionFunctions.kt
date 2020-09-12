package com.dc.learncoroutine.utils

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast

fun Context.showToast(message: String?, duration: Int = Toast.LENGTH_SHORT) {
    message?.let {
        Toast.makeText(this, message, duration).show()
    }
}

fun <T> Context.openActivity(
    it: Class<T>,
    clearTask: Boolean = false,
    bundleKey: String = "",
    bundle: Bundle? = null
) {
    val intent = Intent(this, it)
    if (clearTask) {
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
    } else {
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
    }
    intent.putExtra(bundleKey, bundle)
    startActivity(intent)
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}
