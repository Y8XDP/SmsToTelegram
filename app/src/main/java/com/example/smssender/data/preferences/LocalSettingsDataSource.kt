package com.example.smssender.data.preferences

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalSettingsDataSource @Inject constructor(
    private val sharedPrefs: SharedPreferences) {

    fun getNumber(): String {
        return sharedPrefs.getString(SHARED_PREF_NUMBER, "") ?: ""
    }

    fun getBotActiveChatId(): Long {
        return sharedPrefs.getLong(SHARED_PREF_BOT_ACTIVE_CHAT_ID, 0L)
    }

    fun setNumber(number: String) {
        sharedPrefs.edit().putString(SHARED_PREF_NUMBER, number).apply()
    }

    fun setBotActiveChatId(chatId: Long) {
        sharedPrefs.edit().putLong(SHARED_PREF_BOT_ACTIVE_CHAT_ID, chatId).apply()
    }

    fun getBotToken() : String{
        return sharedPrefs.getString(SHARED_PREF_BOT_TOKEN, "") ?: ""
    }

    fun setBotToken(token: String) {
        sharedPrefs.edit().putString(SHARED_PREF_BOT_TOKEN, token).apply()
    }

    companion object {
        private const val SHARED_PREF_NUMBER = "number"
        private const val SHARED_PREF_BOT_ACTIVE_CHAT_ID = "bot_active_chat_id"
        private const val SHARED_PREF_BOT_TOKEN = "bot_token"
    }
}