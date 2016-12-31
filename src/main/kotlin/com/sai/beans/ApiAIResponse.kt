package com.sai.beans

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude

/**
 * Created by sainageswar on 31/12/16.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
class Context (
    var name: String = "",
    var lifespan: Int = 2,
    var parameters: Map<String, Any> = mutableMapOf<String, Any>()
)

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
class ApiAIResponse (
    var speech: String = "",
    var displayText: String = "",
    var data: Map<String, Any>? = null,
    var contextOut: List<Context>? = null,
    var source: String = "GuruServer"
)