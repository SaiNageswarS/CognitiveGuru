package com.sai.bots

import com.sai.bots.commands.AddKnowledgeCommand
import com.sai.bots.commands.AddTaskCommand
import com.sai.bots.commands.CommandRegistry
import com.sai.bots.commands.ShowTasksCommand
import com.sai.bots.messageHandlers.MessageHandlers
import com.sai.bots.messageHandlers.ReplyConstants
import com.sai.models.CgUser
import com.sai.models.Task
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.telegram.telegrambots.api.objects.Message
import org.telegram.telegrambots.api.objects.Update
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import java.util.concurrent.Executors
import java.util.function.BiConsumer

/**
 * Created by sainageswar on 16/10/16.
 */
@Component
class UpscTelegramBot (
        @Value("\${telegram.upscBotUsername}") val upscBotUsername: String,
        @Value("\${telegram.upscBotToken}") val upscBotToken: String,
        @Autowired val botDataServices: BotDataServices,
        @Autowired val addTaskCommand: AddTaskCommand,
        @Autowired val showTasksCommand: ShowTasksCommand,
        @Autowired val addKnowledgeCommand: AddKnowledgeCommand
    ): TelegramLongPollingBot() {

    val commandRegistry = CommandRegistry()

    init {
        commandRegistry.register(addTaskCommand,
                                 showTasksCommand,
                                 addKnowledgeCommand)
        commandRegistry.defaultConsumer = BiConsumer { absSender, message ->
            val reply = MessageHandlers.handleSimpleTextMessage(message.chatId.toString(),
                            ReplyConstants.UNKNOWN_COMMAND)
            absSender.sendMessage(reply)
        }
    }

    // creating a thread pool of 10 threads
    private val exec = Executors.newFixedThreadPool(2)

    override fun getBotUsername(): String = upscBotUsername

    override fun getBotToken(): String = upscBotToken

    override fun onUpdateReceived(update: Update?) {
        if(update != null && update.hasMessage()){
            exec.submit {
                val message: Message = update.getMessage();

                val cgUser = botDataServices.getCgUser(message.from)
                when {
                    cgUser == null -> handleNoUser(message)
                    message.isCommand -> commandRegistry.executeCommand(this, message, cgUser)
                    else -> processNonCommandUpdate(message, cgUser)
                }
            }
        }
    }

    fun handleNoUser(message: Message) {
        botDataServices.saveUser(
                CgUser(telegramUserId = message.from.id,
                        userContext = UserContext.ENTER_EMAIL))
        val sendMessage = MessageHandlers.handleForceReply(message.chatId.toString(),
                ReplyConstants.ENTER_EMAIL)
        sendMessage(sendMessage)
    }

    fun processNonCommandUpdate(message: Message, cgUser: CgUser) {
        val sendMessageRequest = when(cgUser.userContext) {

            UserContext.ENTER_EMAIL -> {
                botDataServices.updateUserEmail(cgUser, message.text)
                MessageHandlers.handleSimpleTextMessage(message.chatId.toString(),
                        ReplyConstants.WELCOME_MESSAGE)
            }

            UserContext.ADD_TASK -> {
                botDataServices.createTask(Task(jobString = message.text, cgUser = cgUser))
                cgUser.userContext = UserContext.NO_CONTEXT
                botDataServices.saveUser(cgUser)
                MessageHandlers.handleSimpleTextMessage(message.chatId.toString(), "Task Added")
            }

            UserContext.ADD_KNOWLEDGE -> {
                MessageHandlers.handleSimpleTextMessage(message.chatId.toString(), "Thank you")
            }

            else ->
                MessageHandlers.handleEchoMessage(message)
        }
        sendMessage(sendMessageRequest)
    }
}