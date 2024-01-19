package com.teamsparta.todoapp.domain.comment.dto

data class CommentRequest(
    val userId: Long,
//    val nickname: String,
    val content: String,
//    val password: String,
)
