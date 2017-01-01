package com.sai.bot

import com.sai.beans.ApiAIRequest
import com.sai.beans.ApiAIResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.security.SecureRandom

/**
 * Created by sainageswar on 01/01/17.
 */
@Component
class Router @Autowired constructor(val botController: BotController) {
    private val random = SecureRandom()

    fun getResponse(request: ApiAIRequest): ApiAIResponse {
        if (request.user == null) {
            return botController.handleNoUser(request)
        }

        val action = Actions.valueOf(request.result.action.toUpperCase())
        if (action.getStaticResponses().isNotEmpty()) {
            // if static messages are configured, return a standard static message
            val randomIndex = random.nextInt(action.getStaticResponses().size)
            return ApiAIResponse(speech = action.getStaticResponses()[randomIndex])
        }

        //for non-static responses
        val nonStaticResponse = when(action) {
            Actions.TASK_ADD -> botController.addTask(request)
            Actions.TASK_SHOWALL -> botController.showTasks(request)

            else -> ApiAIResponse(speech = "Could you please repeat")
        }

        return nonStaticResponse
    }
}