package com.jhonny.social.data.entities.user

import com.jhonny.social.data.Result
import com.jhonny.social.domain.entities.DomainUser

interface UserDataSource {
    suspend fun getCocktails(page: Int): Result<DomainUser?>

    suspend fun getCocktailsByName(name: String): Result<DomainUser?>

}