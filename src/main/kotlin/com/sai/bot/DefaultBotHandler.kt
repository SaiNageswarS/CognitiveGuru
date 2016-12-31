package com.sai.bot

import com.sai.beans.ApiAIRequest
import com.sai.beans.chatClients.GuruClientAPIRequest
import com.sai.models.CgUser
import com.sai.repositories.DataRepository


/**
 * Created by sainageswar on 24/12/16.
 */
open class DefaultBotHandler(dataRepo: DataRepository, request: ApiAIRequest) :
        BotHandler(dataRepo, request) {

    val originalRequest: GuruClientAPIRequest? = request.originalRequest as? GuruClientAPIRequest

    override fun getUser(): CgUser? {
        return dataRepo.userRepo.findOneByEmail(originalRequest?.email ?: "agent@guruapi.com")
    }
}