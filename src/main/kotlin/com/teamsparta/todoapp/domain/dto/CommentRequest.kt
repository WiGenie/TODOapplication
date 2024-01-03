package com.teamsparta.todoapp.domain.dto

data class CommentRequest(
    val nickname: String,
    val content: String,
    val password: String,
)
