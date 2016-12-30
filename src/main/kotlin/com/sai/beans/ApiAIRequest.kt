package com.sai.beans

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import java.util.*

/**
 * Created by sainageswar on 30/12/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
open class ApiAIRequest {
    var id: String? = null
    var timestamp: Date? = null
    var originalRequest: OriginalRequest? = null
    var result: ApiAIResult? = null
}