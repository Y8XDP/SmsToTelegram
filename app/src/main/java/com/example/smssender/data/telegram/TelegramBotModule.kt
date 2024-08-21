package com.example.smssender.data.telegram

import com.example.smssender.data.preferences.LocalSettingsDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class TelegramBotModule {

    @Provides
    fun provideMessageBot(dataSource: LocalSettingsDataSource) : MessageBot {
        return MessageBot(dataSource)
    }
}