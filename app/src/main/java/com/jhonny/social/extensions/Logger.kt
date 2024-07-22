package com.jhonny.social.extensions

import android.util.Log

const val Users = "Users"

fun log(message: String, tag: String = Users) {
    Log.e(tag, "log: $message")
}
