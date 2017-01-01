package com.sai.beans

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude

/**
 * Created by sainageswar on 30/12/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
class ApiAIResult (
    var source: String? = null,
    var resolvedQuery: String? = null,
    var speech: String? = null,
    var action: String = "",
    var actionIncomplete: Boolean? = null,
    var parameters: Map<String, Any> = mapOf(),
    var contexts: List<Context> = listOf(),
    var metadata: Map<String, Any> = mapOf()
)