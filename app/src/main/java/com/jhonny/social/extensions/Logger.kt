package com.jhonny.social.extensions

import android.util.Log

const val Beers = "Beers"

fun log(message: String, tag: String = Beers) {
    Log.e(tag, "log: $message")
}
