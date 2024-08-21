package com.example.smssender.telegram

interface MessageReceiver {
    fun onMessageReceived(newMessage: String)
}