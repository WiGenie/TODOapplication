package com.teamsparta.todoapp.domain.todo.exception

data class InvalidCredentialException(
    override val message: String? = "The credentail is invalid"
) : RuntimeException()