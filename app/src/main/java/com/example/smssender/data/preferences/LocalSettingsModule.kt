package com.example.smssender.data.preferences

import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class LocalSettingsModule {

    @Provides
    fun provideLocalSettingsDataSource(sharedPrefs: SharedPreferences) : LocalSettingsDataSource {
        return LocalSettingsDataSource(sharedPrefs)
    }
}