package com.teamsparta.todoapp.domain.comment.dto

data class CommentResponse(
    val id: Long,
    val nickname: String,
    val content: String,
)
