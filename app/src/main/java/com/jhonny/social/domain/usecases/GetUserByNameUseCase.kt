package com.jhonny.social.domain.usecases

import com.jhonny.social.di.IoDispatcher
import com.jhonny.social.domain.entities.DomainUser
import com.jhonny.social.domain.errors.Result
import com.jhonny.social.domain.repository.UserDataSource

import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetUserByNameUseCase @Inject constructor(
    private val userDataSource: UserDataSource,
    @IoDispatcher dispatcher: CoroutineDispatcher
) {

    suspend fun execute(parameters: String): Result<DomainUser?> {
        return userDataSource.getUsersByName(parameters)
    }

}