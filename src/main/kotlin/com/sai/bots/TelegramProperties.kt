package com.sai.bots

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

/**
 * Created by sainageswar on 18/10/16.
 */

@EnableConfigurationProperties
@ConfigurationProperties(prefix = "telegram")
class TelegramProperties (
    val upscBotUsername: String = "",
    val upscBotToken: String = ""
)