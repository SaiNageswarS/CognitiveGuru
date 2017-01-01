package com.sai.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.sai.beans.ApiAIRequest
import com.sai.beans.ApiAIResponse
import com.sai.beans.Context
import com.sai.bot.Middleware
import com.sai.bot.Router
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
class ApiAIController @Autowired constructor(val middleware: Middleware, val router: Router) {
    private val log = LoggerFactory.getLogger(ApiAIController::class.java)

    @PostMapping
    fun handleMessage(@RequestBody request: ApiAIRequest): ApiAIResponse {
        log.info("Request:: \n" + ObjectMapper().writerWithDefaultPrettyPrinter()
                .writeValueAsString(request))

        val preProcessedRequest = middleware.preProcessRequest(request)
        val response = router.getResponse(preProcessedRequest)

        log.info("Response:: \n" + ObjectMapper().writerWithDefaultPrettyPrinter()
                .writeValueAsString(response))
        return response
    }
}