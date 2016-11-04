package com.sai.repositories

import com.sai.models.CgUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

/**
 * Created by sainageswar on 14/10/16.
 */
interface CgUserRepository : JpaRepository<CgUser, Long> {
    fun findOneByTelegramUserId(telegramUserId: Int): CgUser?
    fun findOneByEmail(email: String): CgUser?

    /*
     * A task is active if finishedOn is null
     * Find users for whom no active tasks exist
     *  */

    @Query(" SELECT u FROM CgUser u WHERE NOT EXISTS " +
                "( SELECT t FROM Task t " +
                    " WHERE t.cgUser.id = u.id AND t.finishedOn IS NULL ) ")
    fun findUsersWithNoActiveTasks(): List<CgUser>

    /*
     * A task is overdue if finishedOn is null for more than 48 hrs of creation.
     * Find users for whom overdue tasks exist
     *  */

    @Query(" SELECT u FROM CgUser u WHERE EXISTS " +
            "( SELECT t FROM Task t " +
            " WHERE t.cgUser.id = u.id AND t.finishedOn IS NULL " +
            " AND EXTRACT(EPOCH FROM CURRENT_TIMESTAMP - t.created_at)/3600 >= 48 )")
    fun findUsersWithOverdueTasks(): List<CgUser>

}