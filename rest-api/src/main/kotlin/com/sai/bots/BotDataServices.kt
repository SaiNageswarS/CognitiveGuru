package com.sai.bots

import com.sai.ApplicationContextProvider
import com.sai.models.User
import com.sai.models.Task
import com.sai.repositories.TaskRepository
import com.sai.repositories.UserRepository
import java.sql.Timestamp

/**
 * Created by sainageswar on 16/10/16.
 */

object BotDataServices {
    private var userRepository:UserRepository =
            ApplicationContextProvider.appContext!!.getBean("userRepository") as UserRepository
    private var taskRepository:TaskRepository =
            ApplicationContextProvider.appContext!!.getBean("taskRepository") as TaskRepository

    fun handleNewTelegramUser(email: String, telegramUser: org.telegram.telegrambots.api.objects.User) {
        var user = userRepository.findOneByEmail(email)
        if (user == null) {
            user = User(email=email)
        }
        user.telegramUserId = telegramUser.id
        userRepository.save(user)
    }

    fun createTask(jobString: String, telegramUser: org.telegram.telegrambots.api.objects.User) {
        val user = userRepository.findOneByTelegramUserId(telegramUser.id)
        val currentDate = Timestamp(System.currentTimeMillis())
        val task = Task(jobString = jobString, user = user, created_at = currentDate)
        taskRepository.save(task)
    }

    fun getTasksList(telegramUser: org.telegram.telegrambots.api.objects.User): String {
        val tasks = taskRepository.findByUser_TelegramUserId(telegramUser.id)
        val jobStrings = tasks.map { x -> x.id.toString() + ". " + x.jobString }
        return jobStrings.joinToString(separator = "\n")
    }
}
