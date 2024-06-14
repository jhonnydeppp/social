package com.jhonny.social.data.util.error

interface ErrorHandler {
    fun getError(throwable: Throwable): ErrorSource
}
