package com.example.smssender.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.telephony.SmsMessage
import com.example.smssender.data.telegram.MessageBot

class SMSReceiver() : BroadcastReceiver() {

    private var bot: MessageBot? = null

    override fun onReceive(p0: Context?, p1: Intent?) {
        try{
            for (sms: SmsMessage in Telephony.Sms.Intents.getMessagesFromIntent(p1)){
                bot?.sendMessage(sms.displayMessageBody)
            }
        }catch (e: Exception){

        }
    }

    fun passBot(bot: MessageBot){
        this.bot = bot
    }
}