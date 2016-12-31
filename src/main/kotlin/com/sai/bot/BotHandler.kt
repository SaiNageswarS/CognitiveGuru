package com.sai.bot

import com.sai.beans.ApiAIRequest
import com.sai.beans.chatClients.TelegramApiAIRequest
import com.sai.models.CgUser
import com.sai.repositories.DataRepository

/**
 * Created by sainageswar on 23/12/16.
 */

abstract class BotHandler (val dataRepo: DataRepository,
                           val apiAIRequest: ApiAIRequest) {
    abstract fun getUser(): CgUser?

    companion object BotHandler {
        fun getHandler(dataRepo: DataRepository, request: ApiAIRequest): com.sai.bot.BotHandler {
            return when (request.originalRequest) {
                is TelegramApiAIRequest -> TelegramBotHandler(dataRepo, request)
                else -> DefaultBotHandler(dataRepo, request)
            }
        }
    }
}