package com.sai.beans.chatClients

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.sai.beans.OriginalRequest
import org.telegram.telegrambots.api.objects.Update

/**
 * Created by sainageswar on 30/12/16.
 */

class TelegramApiAIRequest (
    var data: Update = Update()
): OriginalRequest()
