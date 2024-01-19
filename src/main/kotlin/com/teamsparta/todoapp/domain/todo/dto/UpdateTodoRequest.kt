package com.teamsparta.todoapp.domain.todo.dto

data class UpdateTodoRequest(
    val userId: Long,
    val title: String,
//    val name: String,
    val detail: String,
)
