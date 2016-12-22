package com.sai.controllers

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Created by sainageswar on 22/12/16.
 */
@RestController
@RequestMapping("/apiai")
class ApiAI {
    @PostMapping
    fun handleMessage(@RequestBody request: MutableMap<Any, Any>): Map<Any, Any> {
        println(request)
        request["responseBy"] = "kotlin"
        return request
    }
}