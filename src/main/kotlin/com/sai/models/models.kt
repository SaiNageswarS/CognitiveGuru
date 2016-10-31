package com.sai.models

/**
 * Created by sainageswar on 14/10/16.
 */
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.hibernate.validator.constraints.Email
import java.sql.Timestamp
import javax.persistence.*

/**
 * Created by sainageswar on 14/10/16.
 */

@Entity
class CgUser(
        @Id @GeneratedValue(strategy = javax.persistence.GenerationType.AUTO) var id: Long = 0,
        @Column(unique = true) @Email var email: String = "",
        var password: String = "",
        @Column(unique = true) var telegramUserId: Int? = null,
        @CreationTimestamp var created_at: Timestamp? = null,
        @UpdateTimestamp var updated_at: Timestamp? = null
)

@Entity
class Task(
        var jobString: String = "",
        var finishedOn: Timestamp? = null,
        @Id @GeneratedValue(strategy = javax.persistence.GenerationType.AUTO) var id: Long = 0,
        var dueDate: Timestamp? = null,
        @ManyToOne var parentTask: Task? = null,
        @ManyToOne var cgUser: CgUser? = null,
        @CreationTimestamp var created_at: Timestamp? = null,
        @UpdateTimestamp var updated_at: Timestamp? = null
)

