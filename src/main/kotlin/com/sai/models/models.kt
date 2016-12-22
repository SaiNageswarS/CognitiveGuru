package com.sai.models

/**
 * Created by sainageswar on 14/10/16.
 */
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.hibernate.validator.constraints.Email
import java.sql.Timestamp
import java.util.*
import javax.persistence.*

/**
 * Created by sainageswar on 14/10/16.
 */

enum class UserContext {
    ENTER_EMAIL,
    ADD_TASK,
    NO_CONTEXT
}

@Entity
class CgUser(
        @Id @GeneratedValue(strategy = javax.persistence.GenerationType.AUTO) var id: Long = 0,
        @Email var email: String = "",
        var password: String = "",
        @Column(unique = true) var telegramUserId: Int? = null,
        @Column(unique = true) var userUUID: String = UUID.randomUUID().toString(),
        @CreationTimestamp val created_at: Timestamp = Timestamp(System.currentTimeMillis()),
        @UpdateTimestamp var updated_at: Timestamp? = null,
        @Enumerated(EnumType.STRING) var userContext: UserContext = UserContext.NO_CONTEXT
)

@Entity
class Task(
        var jobString: String = "",
        var finishedOn: Timestamp? = null,
        @Id @GeneratedValue(strategy = javax.persistence.GenerationType.AUTO) var id: Long = 0,
        var dueDate: Timestamp? = null,
        @ManyToOne var parentTask: Task? = null,
        @ManyToOne var cgUser: CgUser? = null,
        @CreationTimestamp val created_at: Timestamp = Timestamp(System.currentTimeMillis()),
        @UpdateTimestamp var updated_at: Timestamp? = null
)

