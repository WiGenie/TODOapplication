package com.teamsparta.todoapp.domain.dto

data class CommentResponse(
    val id: Long,
    val nickname: String,
    val content: String,
    val password: String,
)
