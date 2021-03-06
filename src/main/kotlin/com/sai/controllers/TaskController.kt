package com.sai.controllers

import com.sai.repositories.TaskRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import com.sai.models.Task

/**
 * Created by sainageswar on 14/10/16.
 */
@RestController
@RequestMapping("/task")
class TaskController @Autowired constructor(val repository: TaskRepository){


    @GetMapping("/{userUUID}")
    fun findByUserUUID(@PathVariable userUUID: String,
                       @RequestParam("taskType", required = false) taskType: String?): List<Task> {
        return when(taskType) {
            "Incomplete" -> repository.findIncompleteTasksByUserUUID(userUUID)
            "Complete" -> repository.findCompletedTasksByUserUUID(userUUID)
            else -> repository.findAllTasksByUserUUID(userUUID)
        }
    }

    @PostMapping
    fun save(@RequestBody task: Task) = repository.save(task)
}