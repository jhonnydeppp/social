package com.jhonny.social.di

import com.jhonny.social.data.entities.user.UserRepository
import com.jhonny.social.data.entities.user.local.UserLocalDataSource
import com.jhonny.social.data.entities.user.mapper.UserMapper
import com.jhonny.social.data.entities.user.remote.UserRemoteDataSource
import com.jhonny.social.data.entities.user.remote.UserRemoteDataSourceImpl
import com.jhonny.social.data.entities.user.service.UserService
import com.jhonny.social.domain.repository.UserDataSource
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
    fun userRepositoryProvider(
        userRemoteDataSource: UserRemoteDataSource,
        userMapper: UserMapper,
        userLocalDataSource: UserLocalDataSource
    ) = UserRepository(
        userRemoteDataSource,
        userLocalDataSource,
        userMapper
    )

    @Singleton
    @Provides
    fun userDataSourceImplProvider(
        userService: UserService
    ) = UserRemoteDataSourceImpl(
        userService
    )

    @Singleton
    @Provides
    fun userDataSourceProvider(
        userRemoteDataSource: UserRemoteDataSource,
        userMapper: UserMapper,
        userLocalDataSource: UserLocalDataSource
    ): UserDataSource = UserRepository(
        userRemoteDataSource,
        userLocalDataSource,
        userMapper
    )

    @Singleton
    @Provides
    fun userRemoteDataSourceProvider(
        userService: UserService
    ): UserRemoteDataSource = UserRemoteDataSourceImpl(
        userService
    )

}