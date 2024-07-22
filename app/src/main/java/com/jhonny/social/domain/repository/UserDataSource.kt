package com.jhonny.social.domain.repository

import com.jhonny.social.domain.entities.DomainUser
import com.jhonny.social.domain.entities.Result

interface UserDataSource {

    suspend fun getUsers(page: Int): Result<DomainUser?>

    suspend fun getUsersByName(name: String): Result<DomainUser?>

    suspend fun getFavoritesUsers(): Result<DomainUser?>

    suspend fun addFavoriteUser(domainUser: DomainUser)

    suspend fun deleteFavoriteUser(domainUser: DomainUser)

}