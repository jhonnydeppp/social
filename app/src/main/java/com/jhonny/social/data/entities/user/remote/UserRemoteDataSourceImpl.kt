package com.jhonny.social.data.entities.user.remote

import android.util.Log
import com.jhonny.social.data.entities.user.service.UserService
import com.jhonny.social.data.util.BaseRemoteDataSource
import javax.inject.Inject

class UserRemoteDataSourceImpl @Inject constructor(private val service: UserService) :
    UserRemoteDataSource, BaseRemoteDataSource() {

    override suspend fun getCocktails(page: Int) = getResult {

        val a = service.getCocktails(page = page)
        Log.i(javaClass.simpleName, "getCocktails: $a ")
        a
    }

    override suspend fun getCocktailsByName(name: String) = getResult {
        service.getCocktailsByName(name)
    }

}

