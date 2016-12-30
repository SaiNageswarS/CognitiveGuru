package com.sai.beans

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude

/**
 * Created by sainageswar on 30/12/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
class ApiAIResult {
    var source: String? = null
    var resolvedQuery: String? = null
    var speech: String? = null
    var action: String? = null
    var actionIncomplete: Boolean? = null
}