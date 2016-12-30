package com.sai.repositories

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * Created by sainageswar on 30/12/16.
 */
@Component
class DataRepository @Autowired constructor(
        val userRepo: CgUserRepository,
        val taskRepo: TaskRepository
)