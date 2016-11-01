package com.sai.repositories

import com.sai.models.Task
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

/**
 * Created by sainageswar on 14/10/16.
 */
interface TaskRepository: JpaRepository<Task, Long> {
    fun findByCgUser_TelegramUserId(telegramUserId: Int): List<Task>

    @Query("SELECT task FROM Task task " +
            "WHERE task.cgUser.userUUID=:userUUID " +
            "ORDER BY task.created_at desc")
    fun findAllTasksByUserUUID(@Param("userUUID") userUUID: String): List<Task>

    @Query("SELECT task FROM Task task " +
            "WHERE task.finishedOn IS NULL and task.cgUser.userUUID=:userUUID " +
            "ORDER BY task.created_at desc")
    fun findIncompleteTasksByUserUUID(@Param("userUUID") userUUID: String): List<Task>

    @Query("SELECT task FROM Task task " +
            "WHERE task.finishedOn IS NOT NULL and task.cgUser.userUUID=:userUUID " +
            "ORDER BY task.created_at desc")
    fun findCompletedTasksByUserUUID(@Param("userUUID") userUUID: String): List<Task>
}