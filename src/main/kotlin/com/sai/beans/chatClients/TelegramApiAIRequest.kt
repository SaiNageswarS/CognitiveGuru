package com.sai.beans.chatClients

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.sai.beans.OriginalRequest

/**
 * Created by sainageswar on 30/12/16.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
class TelegramFrom (
    var id: Int = 0,
    var first_name: String = "",
    var last_name: String = "",
    var username: String = ""
)

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
class TelegramMessage (
        var message_id: Int = 0,
        var from: TelegramFrom = TelegramFrom()
)

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
class TelegramUpdate (
    var update_id: String = "",
    var message: TelegramMessage = TelegramMessage()
)

class TelegramApiAIRequest (
    var data: TelegramUpdate = TelegramUpdate()
): OriginalRequest()
