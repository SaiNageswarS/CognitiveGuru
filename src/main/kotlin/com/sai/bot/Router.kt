package com.sai.bot

import com.sai.beans.ApiAIRequest
import com.sai.beans.ApiAIResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * Created by sainageswar on 01/01/17.
 */
@Component
class Router @Autowired constructor(val botController: BotController) {
    fun getResponse(request: ApiAIRequest): ApiAIResponse {
        return when {
            request.user == null ->  botController.handleNoUser(request)

            else -> ApiAIResponse(speech = "Couldn't get you.")
        }
    }
}