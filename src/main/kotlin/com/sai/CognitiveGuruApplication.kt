package com.sai

import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
open class CognitiveGuruApplication {

    private val log = LoggerFactory.getLogger(CognitiveGuruApplication::class.java)

//    @Bean
//    open fun init() = CommandLineRunner {
//        val telegramBotsApi: TelegramBotsApi = TelegramBotsApi()
//        try {
//            telegramBotsApi.registerBot(upscTelegramBot)
//        } catch (e: TelegramApiException) {
//            log.error("Bot registration error", e)
//        }
//    }
}

fun main(args: Array<String>) {
    SpringApplication.run(CognitiveGuruApplication::class.java, *args)
}
