package com.jhonny.social.di

import com.jhonny.social.data.entities.user.mapper.UserMapper
import com.jhonny.social.data.entities.user.mapper.UserMapperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MapperModule {

    @Singleton
    @Provides
    fun userMapperProvider(): UserMapper = UserMapperImpl()
}