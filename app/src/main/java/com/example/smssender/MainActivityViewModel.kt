package com.example.smssender

import android.app.Application
import android.content.IntentFilter
import android.telephony.SmsManager
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import com.example.smssender.common.Transcoder
import com.example.smssender.data.preferences.LocalSettingsDataSource
import com.example.smssender.receivers.SMSReceiver
import com.example.smssender.data.telegram.MessageBot
import com.example.smssender.telegram.MessageReceiver
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val localSettingsDataSource: LocalSettingsDataSource,
    private val bot: MessageBot,
    application: Application)
    : AndroidViewModel(application){

    val number = mutableStateOf("")
    val botToken = mutableStateOf("")

    var smsReceiver = SMSReceiver()

    private val messageReceiver = object: MessageReceiver {
        override fun onMessageReceived(newMessage: String) {
            sendSms(newMessage)
        }
    }

    init {
        localSettingsDataSource.getNumber().let {
            number.value = it
        }

        localSettingsDataSource.getBotToken().let {
            botToken.value = it
        }

        bot.restart(messageReceiver)

        application.registerReceiver(smsReceiver, IntentFilter("android.provider.Telephony.SMS_RECEIVED"))

        smsReceiver.passBot(bot)
    }

    fun sendSms(text: String) {
        try {
            val smsManager = getApplication<Application>().getSystemService(SmsManager::class.java)

            val textParts = arrayListOf<String>()

            val transcodeText = Transcoder.encode(text)

            for (i in 0..transcodeText.length / 60) {
                textParts.add(transcodeText.substring(i * 60, minOf((i + 1) * 60, transcodeText.length)))
            }

            smsManager.sendMultipartTextMessage(number.value, null, textParts, null, null)
        } catch (e: Exception) {

        }
    }

    fun saveSettings() {
        localSettingsDataSource.setNumber(number.value)
        localSettingsDataSource.setBotToken(botToken.value)

        bot.restart(messageReceiver)
    }
}