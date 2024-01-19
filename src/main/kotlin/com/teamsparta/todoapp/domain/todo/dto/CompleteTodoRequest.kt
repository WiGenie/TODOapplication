package com.teamsparta.todoapp.domain.todo.dto

data class CompleteTodoRequest(
    val userId: Long,
    val complete: String,
)
