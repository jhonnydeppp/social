package com.jhonny.social.domain.usecases


import com.jhonny.social.di.IoDispatcher
import com.jhonny.social.domain.entities.DomainUser
import com.jhonny.social.domain.entities.Result
import com.jhonny.social.domain.repository.UserDataSource
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val userDataSource: UserDataSource,
    @IoDispatcher dispatcher: CoroutineDispatcher
) {

    suspend fun execute(parameters: Int): Result<DomainUser?> {
        return userDataSource.getUsers(parameters)
    }

}