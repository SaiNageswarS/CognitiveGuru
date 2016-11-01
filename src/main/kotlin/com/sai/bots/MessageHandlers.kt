package com.sai.bots

import com.sai.ApplicationContextProvider
import com.sai.CognitiveGuruApplication
import org.slf4j.LoggerFactory
import org.telegram.telegrambots.api.methods.send.SendMessage
import org.telegram.telegrambots.api.objects.Message
import org.telegram.telegrambots.api.objects.replykeyboard.ForceReplyKeyboard

/**
 * Created by sainageswar on 16/10/16.
 */

val log = LoggerFactory.getLogger(CognitiveGuruApplication::class.java)
val webserverUrl = ApplicationContextProvider.getProperty("server.webUrl")

fun handleSimpleTextMessage(message: Message, text: String?): SendMessage {
    val sendMessageRequest = SendMessage()
    sendMessageRequest.chatId = message.chatId.toString() //who should get from the message the sender that sent it.
    sendMessageRequest.text = text
    log.info(sendMessageRequest.toString())
    return sendMessageRequest
}

fun handleForceReply(message: Message, text: String?): SendMessage {
    val sendMessageRequest = SendMessage()
    sendMessageRequest.enableMarkdown(true)
    sendMessageRequest.chatId = message.chatId.toString() //who should get from the message the sender that sent it.

//    sendMessageRequest.replyToMessageId = message.getMessageId();
    sendMessageRequest.replyMarkup = ForceReplyKeyboard()
    sendMessageRequest.text = text
    log.info(sendMessageRequest.toString())
    return sendMessageRequest
}

fun handleEchoMessage(message: Message): SendMessage {
    return handleSimpleTextMessage(message, "you said: " + message.text)
}

fun handleNoUser(message: Message): SendMessage {
    return handleForceReply(message, "Please provide email address")
}

/**
 * addtask - Add a new task
 * showtasks - Show all tasks
 */

fun handleCommand(message: Message): SendMessage {
    assert(message.isCommand)

    val commandReplies = mapOf<String, String>(
            "/addtask" to "Please enter task"
    )

    val command = message.entities[0].text
    val sendMessageRequest = when(command) {
        "/addtask" -> handleForceReply(message, commandReplies[command])
        "/showtasks" -> {
//            val tasksListString = BotDataServices.getTasksList(message.from)
            val userUUID = BotDataServices.getCgUser(message.from)!!.userUUID

            val tasksUrl = webserverUrl + userUUID
            handleSimpleTextMessage(message, tasksUrl)
        }
        else -> handleEchoMessage(message)
    }
    return sendMessageRequest
}

fun handleReply(message: Message): SendMessage {
    assert(message.isReply)

    val earlierMessage = message.replyToMessage.text

    val sendMessageRequest: SendMessage = when(earlierMessage) {
        "Please provide email address" -> {
            BotDataServices.handleNewTelegramUser(message.text, message.from)
            handleSimpleTextMessage(message, "Welcome to Guru")
        }
        "Please enter task" -> {
            BotDataServices.createTask(message.text, message.from)
            handleSimpleTextMessage(message, "Task Added")
        }
        else -> handleEchoMessage(message)
    }
    return sendMessageRequest
}



