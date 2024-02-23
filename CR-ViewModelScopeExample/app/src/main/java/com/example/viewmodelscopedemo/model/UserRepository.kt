package com.example.viewmodelscopedemo.model

import kotlinx.coroutines.delay

class UserRepository {
    suspend fun getUsers(): List<User> {
        delay(8000)
        return listOf(
            User(1, "Sam"),
            User(2, "Amy"),
            User(3, "Joe"),
            User(4, "John"),
            User(5, "Adam")
        )
    }
}