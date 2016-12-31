package com.sai.beans

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.sai.beans.chatClients.GuruClientAPIRequest
import com.sai.beans.chatClients.TelegramApiAIRequest

/**
 * Created by sainageswar on 30/12/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "source")
@JsonSubTypes(JsonSubTypes.Type(value = TelegramApiAIRequest::class, name = "telegram"),
              JsonSubTypes.Type(value = GuruClientAPIRequest::class, name = "guruClient"))
abstract class OriginalRequest (
    var source: String? = null
)
