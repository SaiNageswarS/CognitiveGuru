package com.sai.repositories

import org.springframework.data.repository.CrudRepository
import com.sai.models.Task

/**
 * Created by sainageswar on 14/10/16.
 */
interface TaskRepository: CrudRepository<Task, Long> {
    fun findByCgUser_TelegramUserId(telegramUserId: Int): List<Task>
    fun findByCgUser_UserUUID(userUUID: String): List<Task>
}