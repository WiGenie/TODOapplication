package com.teamsparta.todoapp.domain.dto

data class CreateTodoRequest(
        val name: String,
        val date: Long,
        val title: String,
        val detail: String,
)