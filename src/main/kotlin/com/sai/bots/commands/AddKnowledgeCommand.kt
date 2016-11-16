package com.sai.bots.commands

import com.sai.bots.BotDataServices
import com.sai.bots.UserContext
import com.sai.bots.messageHandlers.MessageHandlers
import com.sai.bots.messageHandlers.ReplyConstants
import com.sai.models.CgUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.telegram.telegrambots.api.objects.Chat
import org.telegram.telegrambots.api.objects.User
import org.telegram.telegrambots.bots.AbsSender

/**
 * Created by sainageswar on 15/11/16.
 */
@Component
class AddKnowledgeCommand(
        @Autowired val botDataServices: BotDataServices
    ) :CgBotCommand("addknowledge", "Add to knowledge base") {
    override fun execute(absSender: AbsSender, telegramUser: User, chat: Chat,
                         arguments: Array<out String>, cgUser: CgUser) {
        cgUser.userContext = UserContext.ADD_KNOWLEDGE
        botDataServices.saveUser(cgUser)
        val reply = MessageHandlers.handleForceReply(chat.id.toString(), ReplyConstants.ADD_KNOWLEDGE)
        absSender.sendMessage(reply)
    }
}