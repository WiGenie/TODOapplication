package com.teamsparta.todoapp.domain.todo.dto

data class CreateTodoRequest(
    val userId: Long,
//    val name: String,
    val title: String,
    val detail: String,
)