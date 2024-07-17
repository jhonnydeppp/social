package com.jhonny.social.domain.usecases


import com.jhonny.social.domain.entities.DomainUser
import com.jhonny.social.domain.entities.Result
import com.jhonny.social.domain.repository.UserDataSource
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val userDataSource: UserDataSource
) {

    suspend fun execute(parameters: Int): Result<DomainUser?> {
        return userDataSource.getUsers(parameters)
    }

}