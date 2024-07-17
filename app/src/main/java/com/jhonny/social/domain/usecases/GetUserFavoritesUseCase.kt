package com.jhonny.social.domain.usecases


import com.jhonny.social.domain.entities.DomainUser
import com.jhonny.social.domain.entities.Result
import com.jhonny.social.domain.repository.UserDataSource
import javax.inject.Inject

class GetUserFavoritesUseCase @Inject constructor(
    private val userDataSource: UserDataSource
) {

    suspend fun execute(): Result<DomainUser?> {
        return userDataSource.getFavoritesUsers()
    }

}