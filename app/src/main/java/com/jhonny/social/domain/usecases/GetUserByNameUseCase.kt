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

class GetUserByNameUseCase @Inject constructor(
    private val userDataSource: UserDataSource,
    @IoDispatcher dispatcher: CoroutineDispatcher,
    mapper: UserMapper
) : SuspendMapperUseCase<String, UserPresentation, DomainUser?>(dispatcher, mapper) {

    override suspend fun execute(parameters: String): Result<DomainUser?> {
        return userDataSource.getCocktailsByName(parameters)
    }

}