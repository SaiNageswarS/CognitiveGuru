package com.sai.bot

/**
 * Created by sainageswar on 01/01/17.
 */
enum class Actions {
    INPUT_WELCOME {
        override fun getStaticResponses() = listOf("Hi", "Hello", "Greetings", "Whats up?")
    },
    USER_ONBOARDING {
        override fun getStaticResponses() = listOf<String>()
    },
    HELP {
        override fun getStaticResponses() = listOf("Subscribe to topics.\n Add tasks. \n Show news.")
    },
    TASK_ADD {
        override fun getStaticResponses() = listOf<String>()
    },
    TASK_SHOWALL {
        override fun getStaticResponses() = listOf<String>()
    };

    abstract fun getStaticResponses(): List<String>
}