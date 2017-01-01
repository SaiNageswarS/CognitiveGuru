package com.sai.bot

import com.sai.beans.ApiAIRequest
import com.sai.beans.OriginalRequest
import com.sai.beans.chatClients.GuruClientAPIRequest
import com.sai.beans.chatClients.TelegramApiAIRequest
import com.sai.models.CgUser
import com.sai.repositories.DataRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * Created by sainageswar on 01/01/17.
 */

@Component
class Middleware @Autowired constructor(val dataRepo: DataRepository) {
    private fun validateRequest(request: ApiAIRequest) : ApiAIRequest {
        return request
    }

    private fun addUser(request: ApiAIRequest): ApiAIRequest {
        val user: CgUser? = when (request.originalRequest) {
            is TelegramApiAIRequest -> {
                val telegramId = (request.originalRequest as TelegramApiAIRequest).data.from.id
                dataRepo.userRepo.findOneByTelegramUserId(telegramId)
            }
            else -> {
                val email = (request.originalRequest as? GuruClientAPIRequest)?.email ?: "agent@guruapi.com"
                dataRepo.userRepo.findOneByEmail(email)
            }
        }
        request.user = user
        return request
    }

    fun preProcessRequest(request: ApiAIRequest) =
        request
            .let { validateRequest(it) }
            .let { addUser(it) }
}
