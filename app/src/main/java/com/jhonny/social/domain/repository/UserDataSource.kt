package com.jhonny.social.domain.repository

import com.jhonny.social.domain.errors.Result
import com.jhonny.social.domain.entities.DomainUser

interface UserDataSource {

    suspend fun getCocktails(page: Int): Result<DomainUser?>

    suspend fun getCocktailsByName(name: String): Result<DomainUser?>

}