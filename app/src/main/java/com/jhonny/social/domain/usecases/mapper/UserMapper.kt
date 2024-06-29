package com.jhonny.social.domain.usecases.mapper


import com.jhonny.social.domain.entities.DomainUser
import com.jhonny.social.presenter.entities.UserPresentation


interface UserMapper {
    fun map(info: DomainUser?): UserPresentation
}
