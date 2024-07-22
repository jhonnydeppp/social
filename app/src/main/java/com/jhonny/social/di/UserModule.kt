package com.jhonny.social.di

import com.jhonny.social.domain.usecases.GetUserByNameUseCase
import com.jhonny.social.domain.usecases.GetUserUseCase
import com.jhonny.social.presenter.favorites.FavoriteViewModel
import com.jhonny.social.presenter.main.MainViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UserModule {

    @Provides
    fun beerViewModelProvider(
        getAllBeersUseCase: GetUserUseCase,
        getBeersByNameUseCase: GetUserByNameUseCase
    ) = MainViewModel (
        getAllBeersUseCase,
        getBeersByNameUseCase
    )

    @Provides
    fun favoriteViewModelProvider(

    ) = FavoriteViewModel ()
}