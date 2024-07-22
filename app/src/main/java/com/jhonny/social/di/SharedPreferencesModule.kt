package com.jhonny.social.di

import android.content.Context
import android.content.SharedPreferences
import com.jhonny.social.data.entities.preferences.PreferencesUtils
import com.jhonny.social.data.entities.user.local.preferences.UserLocalPreferencesDataSource
import com.jhonny.social.data.entities.user.local.preferences.UserLocalPreferencesDataSourceImpl
import com.jhonny.social.util.LOCAL_PREFERENCES
import com.jhonny.social.util.PREFERENCES_MODE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class SharedPreferencesModule {

    @Provides
    fun providesSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(LOCAL_PREFERENCES, PREFERENCES_MODE)

    @Provides
    fun providesPreferencesUtils(sharedPreferences: SharedPreferences): PreferencesUtils =
    PreferencesUtils(sharedPreferences)

    @Provides
    fun providesUserLocalPreferencesDataSource(preferencesUtils: PreferencesUtils): UserLocalPreferencesDataSource =
        UserLocalPreferencesDataSourceImpl(preferencesUtils)

}