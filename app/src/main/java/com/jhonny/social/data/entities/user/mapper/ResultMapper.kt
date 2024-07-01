package com.jhonny.social.data.entities.user.mapper

import com.jhonny.social.domain.entities.Result



fun <T> com.jhonny.social.data.Result<T>.toDomainResult(): Result<T> {
    return when (this) {
        is com.jhonny.social.data.Result.Success -> Result.Success(this.data)
        is com.jhonny.social.data.Result.Error -> Result.Error(this.exception)
    }
}