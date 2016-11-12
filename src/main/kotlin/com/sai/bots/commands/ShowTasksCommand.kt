package com.sai.bots.commands

import com.sai.bots.BotDataServices
import com.sai.bots.messageHandlers.MessageHandlers
import com.sai.bots.UserContext
import com.sai.models.CgUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.telegram.telegrambots.api.objects.Chat
import org.telegram.telegrambots.api.objects.User
import org.telegram.telegrambots.bots.AbsSender

/**
 * Created by sainageswar on 06/11/16.
 * AddTask command handler
 */
@Component
class ShowTasksCommand(
        @Autowired val botDataServices: BotDataServices,
        @Value("\${server.webUrl}") val webserverUrl: String
) :CgBotCommand("showtasks", "Show all tasks") {

    override fun execute(absSender: AbsSender, telegramUser: User, chat: Chat,
                         arguments: Array<out String>, cgUser: CgUser) {

        cgUser.userContext = UserContext.NO_CONTEXT
        botDataServices.saveUser(cgUser)

        val userUUID = cgUser.userUUID
        val tasksUrl = webserverUrl + "/#!/tasks/" + userUUID
        val reply = MessageHandlers.handleSimpleTextMessage(chat.id.toString(), tasksUrl)
        absSender.sendMessage(reply)
    }

}