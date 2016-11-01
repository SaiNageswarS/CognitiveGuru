package com.sai.bots

import com.sai.ApplicationContextProvider
import com.sai.models.CgUser
import com.sai.repositories.CgUserRepository
import org.telegram.telegrambots.api.methods.send.SendMessage
import org.telegram.telegrambots.api.objects.Message
import org.telegram.telegrambots.api.objects.Update
import org.telegram.telegrambots.bots.TelegramLongPollingBot

/**
 * Created by sainageswar on 16/10/16.
 */
class UpscTelegramBot: TelegramLongPollingBot() {
    override fun getBotUsername(): String
            = ApplicationContextProvider.getProperty("telegram.upscBotUsername")
    override fun getBotToken(): String
            = ApplicationContextProvider.getProperty("telegram.upscBotToken")

    override fun onUpdateReceived(update: Update?) {
        if(update != null && update.hasMessage()){
            val message: Message = update.getMessage();

            val cgUser: CgUser? = BotDataServices.getCgUser(message.from)

            val sendMessageRequest: SendMessage = when {
                message.isReply ->
                    handleReply(message)
                cgUser == null ->
                    handleNoUser(message)
                message.isCommand ->
                    handleCommand(message)
                else ->
                    handleEchoMessage(message)
            }
            sendMessage(sendMessageRequest)
        }
    }
}