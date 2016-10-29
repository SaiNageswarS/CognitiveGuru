package com.sai.repositories

import org.springframework.data.repository.CrudRepository
import com.sai.models.User

/**
 * Created by sainageswar on 14/10/16.
 */
interface  UserRepository: CrudRepository<User, Long> {
    fun findOneByTelegramUserId(telegramUserId: Int): User?
    fun findOneByEmail(email: String): User?
}