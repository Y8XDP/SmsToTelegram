package com.example.smssender.data.preferences

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class PreferencesModule {

    @Provides
    fun providePrefs(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE)
    }

    companion object {
        private const val SHARED_PREFS_FILE = "shared_prefs"
    }
}