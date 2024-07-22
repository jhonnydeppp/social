package com.jhonny.social.presenter.mapper

import com.jhonny.social.presenter.entities.Result



fun <T> com.jhonny.social.domain.entities.Result<T>.toPresentationResult(): Result<T> {
    return when (this) {
        is com.jhonny.social.domain.entities.Result.Success -> Result.Success(this.data)
        is com.jhonny.social.domain.entities.Result.Error -> Result.Error(this.exception)
    }
}