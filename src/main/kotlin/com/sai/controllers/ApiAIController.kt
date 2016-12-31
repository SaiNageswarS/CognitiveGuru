package com.sai.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.sai.beans.ApiAIRequest
import com.sai.beans.ApiAIResponse
import com.sai.beans.Context
import com.sai.bot.BotHandler
import com.sai.repositories.DataRepository
import org.slf4j.LoggerFactory
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
    private val log = LoggerFactory.getLogger(ApiAIController::class.java)

    @PostMapping
    fun handleMessage(@RequestBody request: ApiAIRequest): ApiAIResponse {
        log.info("Request:: \n" + ObjectMapper().writerWithDefaultPrettyPrinter()
                .writeValueAsString(request))

        val handler = BotHandler.getHandler(dataRepo, request)
        val cgUser = handler.getUser()
        var response = ApiAIResponse(displayText = "I couldn't get that.")

        if (cgUser == null) {
            response = ApiAIResponse(speech = "Hi. Welcome to Cog Guru. Please enter your email",
                    contextOut = listOf(Context(name = "enter_email", lifespan = 1)))
        }

        log.info("Response:: \n" + ObjectMapper().writerWithDefaultPrettyPrinter()
                .writeValueAsString(response))
        return response
    }
}