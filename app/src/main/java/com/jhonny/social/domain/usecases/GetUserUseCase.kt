package com.jhonny.social.domain.usecases

import com.jhonny.social.data.Result
import com.jhonny.social.data.entities.user.UserDataSource
import com.jhonny.social.di.IoDispatcher
import com.jhonny.social.domain.base.SuspendMapperUseCase
import com.jhonny.social.domain.entities.DomainUser
import com.jhonny.social.domain.usecases.mapper.UserMapper
import com.jhonny.social.presenter.entities.UserPresentation
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val userDataSource: UserDataSource,
    @IoDispatcher dispatcher: CoroutineDispatcher,
    mapper: UserMapper
) : SuspendMapperUseCase<Int, UserPresentation, DomainUser?>(dispatcher, mapper) {

    override suspend fun execute(parameters: Int): Result<DomainUser?> {
        return userDataSource.getCocktails(parameters)
    }

}