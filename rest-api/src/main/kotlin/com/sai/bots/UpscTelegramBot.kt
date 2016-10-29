package com.sai.bots

import com.sai.ApplicationContextProvider
import com.sai.models.User
import com.sai.repositories.UserRepository
import org.telegram.telegrambots.api.methods.send.SendMessage
import org.telegram.telegrambots.api.objects.Message
import org.telegram.telegrambots.api.objects.Update
import org.telegram.telegrambots.bots.TelegramLongPollingBot

/**
 * Created by sainageswar on 16/10/16.
 */
class UpscTelegramBot: TelegramLongPollingBot() {
    private var userRepository: UserRepository =
            ApplicationContextProvider.appContext!!.getBean("userRepository") as UserRepository

    override fun getBotUsername(): String
            = ApplicationContextProvider.appContext!!.environment.getProperty("telegram.upscBotUsername")
    override fun getBotToken(): String
            = ApplicationContextProvider.appContext!!.environment.getProperty("telegram.upscBotToken")

    override fun onUpdateReceived(update: Update?) {
        if(update != null && update.hasMessage()){
            val message: Message = update.getMessage();

            val user: User? = userRepository.findOneByTelegramUserId(message.from.id)

            var sendMessageRequest: SendMessage = when {
                message.isReply ->
                    handleReply(message)
                user == null ->
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