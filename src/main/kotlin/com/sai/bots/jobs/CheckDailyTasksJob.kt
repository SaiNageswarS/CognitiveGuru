package com.sai.bots.jobs

import com.sai.bots.UpscTelegramBot
import com.sai.repositories.CgUserRepository
import org.json.JSONObject
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.telegram.telegrambots.api.methods.BotApiMethod
import org.telegram.telegrambots.api.methods.send.SendMessage
import org.telegram.telegrambots.api.objects.Message
import org.telegram.telegrambots.updateshandlers.SentCallback

/**
 * Created by sainageswar on 03/11/16.
 */

val emptyCallback = object: SentCallback<Message> {
    val logger = LoggerFactory.getLogger(CheckDailyTasksJob::class.java)

    override fun onException(method: BotApiMethod<Message>?, exception: Exception?) {
        logger.error("Exception while sending message.")
    }

    override fun onResult(method: BotApiMethod<Message>?, jsonObject: JSONObject?) {
        logger.info("Success while sending message")
    }

    override fun onError(method: BotApiMethod<Message>?, jsonObject: JSONObject?) {
        logger.error("Error while sending message")
    }

}

@Component
class CheckDailyTasksJob @Autowired constructor(val userRepository: CgUserRepository,
                                                val upscTelegramBot: UpscTelegramBot){
    @Scheduled(cron = "00 00 10 * * *", zone = "Asia/Kolkata")
    fun notifyLazyUsers(): Unit {
        val lazyUsers = userRepository.findUsersWithNoActiveTasks()
        lazyUsers.filter { u -> u.telegramUserId != null }
                .forEach { u ->
                    val sendMessage = SendMessage()
                    sendMessage.chatId = u.telegramUserId.toString()
                    sendMessage.text =
                            "For effective preparation of UPSC, it is important to have continuous tasks.\n\n" +

                            "It would also help me guide you in your preparation.\n" +
                            "Please /addtask"
                    upscTelegramBot.sendMessageAsync(sendMessage, emptyCallback)}
    }

    @Scheduled(cron = "00 00 10 * * *", zone = "Asia/Kolkata")
    fun overdueTasksReminder(): Unit {
        val lazyUsers = userRepository.findUsersWithOverdueTasks()
        lazyUsers.filter { u -> u.telegramUserId != null }
                .forEach { u ->
                    val sendMessage = SendMessage()
                    sendMessage.chatId = u.telegramUserId.toString()
                    sendMessage.text = "You have overdue tasks.. Please finish them or update your tasks"
                    upscTelegramBot.sendMessageAsync(sendMessage, emptyCallback)}
    }


}