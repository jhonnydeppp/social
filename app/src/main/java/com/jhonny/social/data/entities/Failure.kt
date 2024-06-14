package com.jhonny.social.data.entities

sealed class Failure : Exception() {
    object NoInternet : Failure()

    object Generic : Failure()

    data class Source(
        val code: String? = null,
        val errorMessage: String? = null,
        val additionalMessage: String? = null
    ) : Failure()
}
