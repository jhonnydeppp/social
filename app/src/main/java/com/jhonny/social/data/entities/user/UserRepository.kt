package com.jhonny.social.data.entities.user

import com.jhonny.social.data.entities.user.entities.UserResponse
import com.jhonny.social.data.entities.user.local.UserLocalDataSource
import com.jhonny.social.data.entities.user.mapper.UserMapper
import com.jhonny.social.data.entities.user.mapper.toDomainResult
import com.jhonny.social.data.entities.user.remote.UserRemoteDataSource
import com.jhonny.social.data.getData
import com.jhonny.social.data.isSuccess
import com.jhonny.social.data.map
import com.jhonny.social.domain.entities.DomainUser
import com.jhonny.social.domain.entities.Result
import com.jhonny.social.domain.repository.UserDataSource
import javax.inject.Inject

class UserRepository @Inject constructor(private val userRemoteDataSource: UserRemoteDataSource,
                                         private val userLocalDataSource: UserLocalDataSource,
                                         private val userMapper: UserMapper
): UserDataSource {

    override suspend fun getUsers(page: Int): Result<DomainUser?> {
        val userResponse = userRemoteDataSource.getUsers(page)
        if(userResponse.isSuccess)
            userLocalDataSource.addNewElementsToList(userResponse.getData()?.results)
        return userResponse
            .map(userMapper::responseToDomain).toDomainResult()
    }

    override suspend fun getUsersByName(name: String) = userRemoteDataSource.getUsersByName(name)
        .map(userMapper::responseToDomain).toDomainResult()

    override suspend fun getFavoritesUsers(): Result<DomainUser?> {
        val favoritesList = userLocalDataSource.getFavorites()
        val userResponse = UserResponse(results = favoritesList ,null)
        return Result.Success(userMapper.responseToDomain(userResponse))

    }

    override suspend fun addFavoriteUser(domainUser: DomainUser) {
        val userResponse = userMapper.domainToResponse(domainUser)
        val userFavorite = userLocalDataSource.getLocalList().find { localUser ->
            localUser.name?.first == userResponse?.results?.first()?.name?.first
        }
        val userList = userLocalDataSource.getLocalList()
        userList.find { it.name?.first == userFavorite?.name?.first }?.isFavorite = true
        userLocalDataSource.setLocalList(userList)
    }

    override suspend fun deleteFavoriteUser(domainUser: DomainUser) {
        val userResponse = userMapper.domainToResponse(domainUser)
        val userFavorite = userLocalDataSource.getLocalList().find { localUser ->
            localUser.name?.first == userResponse?.results?.first()?.name?.first
        }
        val userList = userLocalDataSource.getLocalList()
        userList.find { it.name?.first == userFavorite?.name?.first }?.isFavorite = false
        userLocalDataSource.setLocalList(userList)
    }

}