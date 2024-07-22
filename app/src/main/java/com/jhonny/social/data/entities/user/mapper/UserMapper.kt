package com.jhonny.social.data.entities.user.mapper

import com.jhonny.social.data.entities.user.entities.UserResponse
import com.jhonny.social.domain.entities.DomainUser


interface UserMapper {

    fun responseToDomain(user: UserResponse?): DomainUser?

    fun domainToResponse(user: DomainUser): UserResponse?
}
