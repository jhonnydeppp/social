package com.jhonny.social.data.entities.user.remote

import com.jhonny.social.data.entities.user.service.UserService
import com.jhonny.social.data.util.BaseRemoteDataSource
import javax.inject.Inject

class UserRemoteDataSourceImpl @Inject constructor(private val service: UserService) :
    UserRemoteDataSource, BaseRemoteDataSource() {

    override suspend fun getCocktails(page: Int) = getResult {
        service.getCocktails(page = page)
    }

    override suspend fun getCocktailsByName(name: String) = getResult {
        service.getCocktailsByName(name)
    }

}

