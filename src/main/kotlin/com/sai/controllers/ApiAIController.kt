package com.sai.controllers

import com.sai.beans.ApiAIRequest
import com.sai.bot.BotHandler
import com.sai.repositories.DataRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Created by sainageswar on 22/12/16.
 */
@RestController
@RequestMapping("/apiai")
class ApiAIController @Autowired constructor(val dataRepo: DataRepository) {
    @PostMapping
    fun handleMessage(@RequestBody request: ApiAIRequest): Map<String, Any> {
        val handler = BotHandler.getHandler(dataRepo, request)
        val cgUser = handler.getUser()

        return mapOf("status" to "ok")
    }
}