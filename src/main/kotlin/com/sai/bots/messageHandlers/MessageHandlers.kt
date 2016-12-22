package com.sai.bots.messageHandlers

import org.slf4j.LoggerFactory
import org.telegram.telegrambots.api.methods.send.SendMessage
import org.telegram.telegrambots.api.objects.Message
import org.telegram.telegrambots.api.objects.replykeyboard.ForceReplyKeyboard

/**
 * Created by sainageswar on 16/10/16.
 */
object MessageHandlers {

    private val log = LoggerFactory.getLogger(MessageHandlers::class.java)

    fun handleSimpleTextMessage(chatId: String, text: String?): SendMessage {
        val sendMessageRequest = SendMessage()
                                    .setChatId(chatId).setText(text)
        log.info(sendMessageRequest.toString())
        return sendMessageRequest
    }

    fun handleForceReply(chatId: String, text: String?): SendMessage {
        val sendMessageRequest = SendMessage()
        sendMessageRequest.enableMarkdown(true)
        sendMessageRequest.chatId = chatId //who should get from the message the sender that sent it.

//      sendMessageRequest.replyToMessageId = message.getMessageId();
        sendMessageRequest.replyMarkup = ForceReplyKeyboard()
        sendMessageRequest.text = text
        log.info(sendMessageRequest.toString())
        return sendMessageRequest
    }

    fun handleEchoMessage(message: Message): SendMessage {
        return handleSimpleTextMessage(message.chatId.toString(), "you said: " + message.text)
    }
}




