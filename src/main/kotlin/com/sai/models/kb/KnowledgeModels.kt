package com.sai.models.kb

import javax.persistence.*

/**
 * Created by sainageswar on 13/11/16.
 */

@Entity
data class Subject(
        @Id @GeneratedValue(strategy = javax.persistence.GenerationType.AUTO) val id: Long = 0,
        var name: String = ""
)

@Entity
data class Topic(
        @Id @GeneratedValue(strategy = javax.persistence.GenerationType.AUTO) val id: Long = 0,
        var name: String = "",
        @ManyToOne var subject: Subject? = null
)

@Entity
data class Fact(
        @Id @GeneratedValue(strategy = javax.persistence.GenerationType.AUTO) val id: Long = 0,
        @ManyToOne var topic: Topic? = null,
        var question: String = "",
        var answer: String = "",
        @ElementCollection var options: Set<String> = mutableSetOf(),
        var description: String = ""
)