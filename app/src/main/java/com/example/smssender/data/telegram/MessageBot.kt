package com.example.smssender.data.telegram

import com.example.smssender.data.preferences.LocalSettingsDataSource
import com.example.smssender.telegram.MessageReceiver
import com.pengrad.telegrambot.TelegramBot
import com.pengrad.telegrambot.TelegramException
import com.pengrad.telegrambot.UpdatesListener
import com.pengrad.telegrambot.model.Update
import com.pengrad.telegrambot.request.SendMessage
import javax.inject.Inject

class MessageBot @Inject constructor(
    private val localDataSource: LocalSettingsDataSource) {

    var bot: TelegramBot? = null
    var currentChatId: Long? = null

    init {
        currentChatId = localDataSource.getBotActiveChatId()
        bot = TelegramBot(localDataSource.getBotToken())
    }

    fun sendMessage(string: String){
        if (currentChatId == null)  return
        val response = bot?.execute(SendMessage(currentChatId, string))
    }

    private fun updateChatId(chatId: Long){
        currentChatId = chatId
        localDataSource.setBotActiveChatId(chatId)
    }

    fun restart(receiver: MessageReceiver) {
        bot?.removeGetUpdatesListener()
        //bot = null
        bot?.shutdown()

        bot = TelegramBot(localDataSource.getBotToken())
        startListen(receiver)
    }

    private fun startListen(receiver: MessageReceiver){
        bot?.setUpdatesListener({ updates: List<Update> ->
            try{
                updates.forEach { update ->
                    println("new messsage " + update.message().text())

                    if (update.message().text() == "/reg"){
                        updateChatId(update.message().chat().id())
                        sendMessage("registered chatId = ${currentChatId}")
                    }else{
                        receiver.onMessageReceived(update.message().text())
                    }
                }
            }catch (e: Exception){

            }

            UpdatesListener.CONFIRMED_UPDATES_ALL
        },
            { e: TelegramException ->
                if (e.response() != null) {
                    // got bad response from telegram
                    e.response().errorCode()
                    e.response().description()
                } else {
                    // probably network error
                    e.printStackTrace()
                }
            })
    }
}