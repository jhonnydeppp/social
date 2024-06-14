package com.jhonny.social.data.util


import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.jhonny.social.data.Result
import com.jhonny.social.data.util.error.ErrorApi
import com.jhonny.social.data.util.error.ErrorHandler
import com.jhonny.social.data.util.error.ErrorSource
import com.jhonny.social.extensions.log
import com.jhonny.social.extensions.toDomain
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException

abstract class BaseRemoteDataSource : ErrorHandler {

    protected suspend fun <Out> getResult(
        call: suspend () -> Out
    ): Result<Out> = try {
        Result.Success(call())
    } catch (e: Exception) {
        log("error ---> $e ")
        Result.Error(exception = getError(e).toDomain())
    }

    override fun getError(throwable: Throwable): ErrorSource = when (throwable) {
        is IOException -> ErrorSource.Network
        is HttpException -> getErrorFromBody(throwable.response()?.errorBody(), throwable.code())
        else -> ErrorSource.Unknown
    }

    private fun getErrorFromBody(errorBody: ResponseBody?, code: Int): ErrorSource {
        return errorBody?.let {
            try {
                val errorApi = Gson().fromJson(it.string(), ErrorApi::class.java)
                ErrorSource.ServiceError(
                    errorCode = code.toString(),
                    errorMessage = errorApi.error,
                    additionalMessage = errorApi.errorCode ?: errorApi.message.toString()
                )
            } catch (jsonException: JsonSyntaxException) {
                ErrorSource.Unknown
            }
        } ?: ErrorSource.Unknown
    }
}
