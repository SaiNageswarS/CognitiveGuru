package com.sai.bots.commands

import com.sai.models.CgUser
import org.telegram.telegrambots.api.objects.Chat
import org.telegram.telegrambots.api.objects.Message
import org.telegram.telegrambots.api.objects.User
import org.telegram.telegrambots.bots.AbsSender
import org.telegram.telegrambots.bots.commands.BotCommand
import java.util.*
import java.util.function.BiConsumer

/**
 * Created by sainageswar on 12/11/16.
 */

class CommandRegistry {
    val commandRegistryMap = mutableMapOf<String, CgBotCommand>()
    var defaultConsumer: BiConsumer<AbsSender, Message>? = null

    fun register(vararg botCommands: CgBotCommand) {
        for (botCommand in botCommands) {
            commandRegistryMap[botCommand.commandIdentifier] = botCommand
        }
    }

    fun executeCommand(absSender: AbsSender, message: Message, cgUser: CgUser): Boolean {
        if (message.hasText()) {
            val text = message.text
            if (text.startsWith(BotCommand.COMMAND_INIT_CHARACTER)) {
                val commandMessage = text.substring(1)
                val commandSplit = commandMessage.split(BotCommand.COMMAND_PARAMETER_SEPARATOR.toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

                val command = commandSplit[0]

                if (commandRegistryMap.containsKey(command)) {
                    val parameters = Arrays.copyOfRange(commandSplit, 1, commandSplit.size)
                    commandRegistryMap[command]!!.execute(absSender, message.from,
                            message.chat, parameters, cgUser)
                    return true
                } else if (defaultConsumer != null) {
                    defaultConsumer!!.accept(absSender, message)
                    return true
                }
            }
        }
        return false
    }
}


abstract class CgBotCommand(commandIdentifier: String, description: String) :
        BotCommand(commandIdentifier, description) {
    override fun execute(absSender: AbsSender?, user: User?, chat: Chat?, arguments: Array<out String>?) {

    }

    abstract fun execute(absSender: AbsSender, telegramUser: User, chat: Chat,
                         arguments: Array<out String>, cgUser: CgUser)

}