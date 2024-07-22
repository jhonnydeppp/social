package com.jhonny.social.domain.usecases


import com.jhonny.social.domain.entities.DomainUser
import com.jhonny.social.domain.repository.UserDataSource
import javax.inject.Inject

class DeleteUserFavoriteUseCase @Inject constructor(
    private val userDataSource: UserDataSource
) {

    suspend fun execute(domainUser: DomainUser){
         userDataSource.deleteFavoriteUser(domainUser)
    }

}