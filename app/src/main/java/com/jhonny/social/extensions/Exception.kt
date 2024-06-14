package com.jhonny.social.extensions

import com.jhonny.social.data.entities.Failure
import com.jhonny.social.data.util.error.ErrorSource

fun Exception.toDomain() = when (this) {
    is ErrorSource.Network -> Failure.NoInternet
    is ErrorSource.ServiceError -> Failure.Source(errorCode, errorMessage, additionalMessage)
    else -> Failure.Generic
}
