package com.jhonny.social.domain.usecases

import com.jhonny.social.domain.entities.DomainUser
import com.jhonny.social.domain.entities.Result
import com.jhonny.social.domain.repository.UserDataSource
import javax.inject.Inject

class GetUserByNameUseCase @Inject constructor(
    private val userDataSource: UserDataSource
) {

    suspend fun execute(parameters: String): Result<DomainUser?> {
        return userDataSource.getUsersByName(parameters)
    }

}