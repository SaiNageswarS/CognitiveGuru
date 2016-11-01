package com.sai

import org.springframework.context.ApplicationContext

/**
 * Created by sainageswar on 18/10/16.
 */
object ApplicationContextProvider {
    var appContext: ApplicationContext? = null

    fun getProperty(key: String): String {
        return appContext!!.environment.getProperty(key)
    }
}