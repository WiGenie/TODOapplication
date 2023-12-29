package com.teamsparta.todoapp.domain.dto

data class TodoResponse(
        val name: String,
        val date: Long,
        val title: String,
        val detail: String,

)