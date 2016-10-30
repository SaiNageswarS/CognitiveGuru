package com.sai.repositories

import org.springframework.data.repository.CrudRepository
import com.sai.models.CgUser

/**
 * Created by sainageswar on 14/10/16.
 */
interface CgUserRepository : CrudRepository<CgUser, Long> {
    fun findOneByTelegramUserId(telegramUserId: Int): CgUser?
    fun findOneByEmail(email: String): CgUser?
}