package com.teamsparta.todoapp.domain.dto

data class UpdateTodoRequest(
        val title: String,
        val name: String,
        val detail: String,
)
