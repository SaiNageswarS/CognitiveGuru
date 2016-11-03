package com.sai.bots

import com.sai.models.CgUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.telegram.telegrambots.api.methods.send.SendMessage
import org.telegram.telegrambots.api.objects.Message
import org.telegram.telegrambots.api.objects.Update
import org.telegram.telegrambots.bots.TelegramLongPollingBot

/**
 * Created by sainageswar on 16/10/16.
 */
@Component
class UpscTelegramBot (
        @Value("\${telegram.upscBotUsername}") val upscBotUsername: String,
        @Value("\${telegram.upscBotToken}") val upscBotToken: String,
        @Autowired val botDataServices: BotDataServices,
        @Autowired val messageHandlers: MessageHandlers
    ): TelegramLongPollingBot() {

    override fun getBotUsername(): String = upscBotUsername

    override fun getBotToken(): String = upscBotToken

    override fun onUpdateReceived(update: Update?) {
        if(update != null && update.hasMessage()){
            val message: Message = update.getMessage();

            val cgUser: CgUser? = botDataServices.getCgUser(message.from)

            val sendMessageRequest: SendMessage = when {
                message.isReply ->
                    messageHandlers.handleReply(message)
                cgUser == null ->
                    messageHandlers.handleNoUser(message)
                message.isCommand ->
                    messageHandlers.handleCommand(message)
                else ->
                    messageHandlers.handleEchoMessage(message)
            }
            sendMessage(sendMessageRequest)
        }
    }
}