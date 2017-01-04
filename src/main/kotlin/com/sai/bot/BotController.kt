package com.sai.bot

import com.sai.beans.ApiAIRequest
import com.sai.beans.ApiAIResponse
import com.sai.beans.Context
import com.sai.beans.chatClients.TelegramApiAIRequest
import com.sai.models.CgUser
import com.sai.models.Task
import com.sai.repositories.DataRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

/**
 * Created by sainageswar on 01/01/17.
 */

@Component
class BotController (@Autowired val dataRepo: DataRepository,
                     @Value("\${server.webUrl}") val webserverUrl: String) {

    fun handleNoUser(request: ApiAIRequest): ApiAIResponse {
        if (request.user != null) {
            throw Exception("User exists")
        }

        // check if email is given by user
        if(request.containsContext("enter_email")) {
            val email = request.result.parameters["email"] as String

            val user = when(request.originalRequest) {
                is TelegramApiAIRequest -> {
                    val telegramId = (request.originalRequest as TelegramApiAIRequest).data.message.from.id
                    CgUser(email = email, telegramUserId = telegramId)
                }
                else ->  CgUser(email = email)
            }

            // save user
            dataRepo.userRepo.save(user)
            return ApiAIResponse(speech = "Hi $email. Welcome to Guru. Your personal assistant. Enter help to know more.")
        }

        return ApiAIResponse(speech = "Hi. Please enter your email to register",
                contextOut = listOf(Context(name = "enter_email", lifespan = 1)))
    }

    fun addTask(request: ApiAIRequest): ApiAIResponse {
        val task = request.result.parameters["task"] as String
        dataRepo.taskRepo.save(Task(jobString = task, cgUser = request.user))
        return ApiAIResponse(speech = "Task added.")
    }

    fun showTasks(request: ApiAIRequest): ApiAIResponse {
        val tasksUrl = webserverUrl + "/#!/tasks/" + request.user?.userUUID
        return ApiAIResponse(speech = tasksUrl)
    }
}