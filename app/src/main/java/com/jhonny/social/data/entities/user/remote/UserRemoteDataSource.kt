package com.jhonny.social.data.entities.user.remote

import com.jhonny.social.data.Result
import com.jhonny.social.data.entities.user.entities.UserResponse

interface UserRemoteDataSource {

    suspend fun getUsers(page: Int): Result<UserResponse?>

    suspend fun getUsersByName(name: String = ""): Result<UserResponse?>
}