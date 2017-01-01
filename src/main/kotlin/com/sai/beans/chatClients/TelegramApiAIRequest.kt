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
    var last_name: String = ""
)

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
class TelegramUpdate (
    var update_id: String = "",
    var from: TelegramFrom = TelegramFrom()
)

class TelegramApiAIRequest (
    var data: Map<String, Any> = mapOf()
): OriginalRequest()
