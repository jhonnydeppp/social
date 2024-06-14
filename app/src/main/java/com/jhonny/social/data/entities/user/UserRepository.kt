package com.jhonny.social.data.entities.user

import com.jhonny.social.data.entities.user.mapper.UserMapper
import com.jhonny.social.data.entities.user.remote.UserRemoteDataSource
import com.jhonny.social.data.map
import javax.inject.Inject

class UserRepository @Inject constructor(private val userRemoteDataSource: UserRemoteDataSource,
                                         private val userMapper: UserMapper
): UserDataSource {

    override suspend fun getCocktails(page: Int) = userRemoteDataSource.getCocktails(page)
        .map(userMapper::responseToDomain)

    override suspend fun getCocktailsByName(name: String) = userRemoteDataSource.getCocktailsByName(name)
        .map(userMapper::responseToDomain)

}