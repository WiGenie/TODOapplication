package com.teamsparta.todoapp.domain.dto

data class TodoResponse(
        val id: Long,
        val name: String,
        val title: String,
        val detail: String?,
        val complete: String,
        val date: String,

        )