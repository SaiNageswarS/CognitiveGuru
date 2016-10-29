package com.sai.repositories

import org.springframework.data.repository.CrudRepository
import com.sai.models.Task

/**
 * Created by sainageswar on 14/10/16.
 */
interface TaskRepository: CrudRepository<Task, Long> {
    fun findByUser_TelegramUserId(telegramUserId: Int): List<Task>
}