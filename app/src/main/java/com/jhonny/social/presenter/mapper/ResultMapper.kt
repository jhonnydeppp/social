package com.jhonny.social.presenter.mapper

import com.jhonny.social.presenter.errors.Result



fun <T> com.jhonny.social.domain.errors.Result<T>.toPresentationResult(): Result<T> {
    return when (this) {
        is com.jhonny.social.domain.errors.Result.Success -> Result.Success(this.data)
        is com.jhonny.social.domain.errors.Result.Error -> Result.Error(this.exception)
    }
}