package com.jhonny.social.data.entities.user

import com.jhonny.social.data.entities.user.mapper.UserMapper
import com.jhonny.social.data.entities.user.mapper.toDomainResult
import com.jhonny.social.data.entities.user.remote.UserRemoteDataSource

import com.jhonny.social.data.map
import com.jhonny.social.domain.entities.DomainUser
import com.jhonny.social.domain.errors.Result
import com.jhonny.social.domain.repository.UserDataSource
import javax.inject.Inject

class UserRepository @Inject constructor(private val userRemoteDataSource: UserRemoteDataSource,
                                         private val userMapper: UserMapper
): UserDataSource {

    override suspend fun getCocktails(page: Int): Result<DomainUser?> {
        return userRemoteDataSource.getCocktails(page)
            .map(userMapper::responseToDomain).toDomainResult()
    }

    override suspend fun getCocktailsByName(name: String) = userRemoteDataSource.getCocktailsByName(name)
        .map(userMapper::responseToDomain).toDomainResult()

}