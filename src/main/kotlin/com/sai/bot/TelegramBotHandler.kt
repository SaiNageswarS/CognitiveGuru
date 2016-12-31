package com.sai.bot

import com.sai.beans.ApiAIRequest
import com.sai.beans.chatClients.TelegramApiAIRequest
import com.sai.models.CgUser
import com.sai.repositories.DataRepository

/**
 * Created by sainageswar on 24/12/16.
 */
class TelegramBotHandler (dataRepo: DataRepository, request: ApiAIRequest) :
        BotHandler(dataRepo, request) {

    val originalRequest: TelegramApiAIRequest = request.originalRequest as TelegramApiAIRequest

    override fun getUser(): CgUser? {
        return dataRepo.userRepo.findOneByTelegramUserId(originalRequest.data.from.id)
    }
}