package com.jhonny.social.data.entities.user.remote

import com.jhonny.social.data.entities.user.service.UserService
import com.jhonny.social.data.util.BaseRemoteDataSource
import javax.inject.Inject

class UserRemoteDataSourceImpl @Inject constructor(private val service: UserService) :
    UserRemoteDataSource, BaseRemoteDataSource() {

    override suspend fun getUsers(page: Int) = getResult {
        service.getUsers(page = page)
    }

    override suspend fun getUsersByName(name: String) = getResult {
        service.getUsersByName(name)
    }

}

