package com.jhonny.social.di

import com.jhonny.social.data.entities.user.local.UserLocalDataSource
import com.jhonny.social.data.entities.user.local.UserLocalDataSourceImpl
import com.jhonny.social.data.entities.user.local.preferences.UserLocalPreferencesDataSource
import com.jhonny.social.domain.usecases.AddUserFavoriteUseCase
import com.jhonny.social.domain.usecases.DeleteUserFavoriteUseCase
import com.jhonny.social.domain.usecases.GetUserByNameUseCase
import com.jhonny.social.domain.usecases.GetUserFavoritesUseCase
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
    fun userViewModelProvider(
        getAllUsersUseCase: GetUserUseCase,
        getUsersByNameUseCase: GetUserByNameUseCase,
        addUserFavoriteUseCase: AddUserFavoriteUseCase,
        deleteUserFavoriteUseCase: DeleteUserFavoriteUseCase
    ) = MainViewModel (
        getAllUsersUseCase,
        getUsersByNameUseCase,
        addUserFavoriteUseCase,
        deleteUserFavoriteUseCase
    )

    @Provides
    fun favoriteViewModelProvider(getUserFavoritesUseCase: GetUserFavoritesUseCase,
                                  addUserFavoriteUseCase: AddUserFavoriteUseCase,
                                  deleteUserFavoriteUseCase: DeleteUserFavoriteUseCase
    ) =
        FavoriteViewModel(getUserFavoritesUseCase, addUserFavoriteUseCase, deleteUserFavoriteUseCase)

    @Provides
    fun userLocalDataSourceProvider(userLocalPreferencesDataSource: UserLocalPreferencesDataSource): UserLocalDataSource =
        UserLocalDataSourceImpl(userLocalPreferencesDataSource)

}