package com.jhonny.social.domain.usecases


import android.util.Log
import com.jhonny.social.domain.errors.Result
import com.jhonny.social.di.IoDispatcher
import com.jhonny.social.domain.base.SuspendMapperUseCase
import com.jhonny.social.domain.entities.DomainUser
import com.jhonny.social.domain.mapper.Mapper
import com.jhonny.social.domain.repository.UserDataSource
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val userDataSource: UserDataSource,
    @IoDispatcher dispatcher: CoroutineDispatcher
) {

    suspend fun execute(parameters: Int): Result<DomainUser?> {
        val a = userDataSource.getCocktails(parameters)
        Log.i("UserRemoteDataSourceImpl", "execute: $a")
        //return userDataSource.getCocktails(parameters)
        return a
    }

}