package com.teamsparta.todoapp.domain.user.dto

data class UserRequest(
    val email: String,
    val password: String,
    val nickname: String,
)
