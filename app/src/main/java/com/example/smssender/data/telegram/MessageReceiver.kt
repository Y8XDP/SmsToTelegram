package com.example.smssender.data.telegram

interface MessageReceiver {
    fun onMessageReceived(newMessage: String)
}