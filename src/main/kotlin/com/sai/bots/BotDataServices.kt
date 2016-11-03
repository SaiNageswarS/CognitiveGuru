package com.sai.bots

import com.sai.models.CgUser
import com.sai.models.Task
import com.sai.repositories.TaskRepository
import com.sai.repositories.CgUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.sql.Timestamp
import org.telegram.telegrambots.api.objects.User
/**
 * Created by sainageswar on 16/10/16.
 */
@Component
class BotDataServices @Autowired constructor(val cgUserRepository: CgUserRepository,
                                             val taskRepository:TaskRepository){

    fun handleNewTelegramUser(email: String, telegramUser: User) {
        var user = cgUserRepository.findOneByEmail(email)
        if (user == null) {
            user = CgUser(email=email)
        }
        user.telegramUserId = telegramUser.id
        cgUserRepository.save(user)
    }

    fun createTask(jobString: String, telegramUser: User) {
        val user = cgUserRepository.findOneByTelegramUserId(telegramUser.id)
        val currentDate = Timestamp(System.currentTimeMillis())
        val task = Task(jobString = jobString, cgUser = user, created_at = currentDate)
        taskRepository.save(task)
    }

    fun getCgUser(telegramUser: User) = cgUserRepository.findOneByTelegramUserId(telegramUser.id)

    fun getTasksList(telegramUser: User): String {
        val tasks = taskRepository.findByCgUser_TelegramUserId(telegramUser.id)
        val jobStrings = tasks.map { x -> x.id.toString() + ". " + x.jobString }
        return jobStrings.joinToString(separator = "\n")
    }
}
