package com.jhonny.social.data.entities.user.service


import com.jhonny.social.data.APIConstants.USER_NAME
import com.jhonny.social.data.APIConstants.ENDPOINT_USERS
import com.jhonny.social.data.APIConstants.PAGE
import com.jhonny.social.data.APIConstants.PER_PAGE
import com.jhonny.social.data.entities.user.entities.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {


    @GET(ENDPOINT_USERS)
    suspend fun getCocktails(
        @Query(PER_PAGE)  perPage: Int = 10,
        @Query(PAGE)  page: Int = 1): UserResponse?

    @GET(ENDPOINT_USERS)
    suspend fun getCocktailsByName(
        @Query(USER_NAME) userName: String,
        @Query(PER_PAGE)  perPage: Int = 10,
        @Query(PAGE)  page: Int = 1
        ): UserResponse?

}