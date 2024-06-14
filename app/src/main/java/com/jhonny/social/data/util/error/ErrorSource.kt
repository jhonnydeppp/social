package com.jhonny.social.data.util.error

sealed class ErrorSource : Exception() {

    object Network : ErrorSource()

    object Unknown : ErrorSource()

    data class ServiceError(
        val errorCode: String,
        val errorMessage: String?,
        val additionalMessage: String?
    ) : ErrorSource()
}
