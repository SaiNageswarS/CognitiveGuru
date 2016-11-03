package com.sai

import com.sai.bots.UpscTelegramBot
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.telegram.telegrambots.TelegramBotsApi
import org.telegram.telegrambots.exceptions.TelegramApiException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext

@SpringBootApplication
open class CognitiveGuruApplication {

    private val log = LoggerFactory.getLogger(CognitiveGuruApplication::class.java)

    @Autowired
    private var upscTelegramBot: UpscTelegramBot? = null

    @Bean
    open fun init() = CommandLineRunner {
        val telegramBotsApi: TelegramBotsApi = TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(upscTelegramBot)
        } catch (e: TelegramApiException) {
            log.error("Bot registration error", e)
        }
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(CognitiveGuruApplication::class.java, *args)
}
