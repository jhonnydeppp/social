package com.jhonny.social.di

import com.jhonny.social.data.entities.user.UserDataSource
import com.jhonny.social.data.entities.user.UserRepository
import com.jhonny.social.data.entities.user.mapper.UserMapper
import com.jhonny.social.data.entities.user.remote.UserRemoteDataSource
import com.jhonny.social.data.entities.user.remote.UserRemoteDataSourceImpl
import com.jhonny.social.data.entities.user.service.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun cocktailRepositoryProvider(
        userRemoteDataSource: UserRemoteDataSource,
        userMapper: UserMapper
    ) = UserRepository(
        userRemoteDataSource,
        userMapper
    )

    @Singleton
    @Provides
    fun cocktailDataSourceImplProvider(
        userService: UserService
    ) = UserRemoteDataSourceImpl(
        userService
    )

    @Singleton
    @Provides
    fun cocktailDataSourceProvider(
        userRemoteDataSource: UserRemoteDataSource,
        userMapper: UserMapper
    ): UserDataSource = UserRepository(
        userRemoteDataSource,
        userMapper
    )

    @Singleton
    @Provides
    fun cocktailRemoteDataSourceProvider(
        userService: UserService
    ): UserRemoteDataSource = UserRemoteDataSourceImpl(
        userService
    )

}