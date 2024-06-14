package com.jhonny.social.data.entities.user.remote

import com.jhonny.social.data.Result
import com.jhonny.social.data.entities.user.entities.UserResponse

interface UserRemoteDataSource {

    suspend fun getCocktails(page: Int): Result<UserResponse?>

    suspend fun getCocktailsByName(name: String = ""): Result<UserResponse?>
}