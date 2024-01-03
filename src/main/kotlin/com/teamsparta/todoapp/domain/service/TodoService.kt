package com.teamsparta.todoapp.domain.service

import com.teamsparta.todoapp.domain.dto.CreateTodoRequest
import com.teamsparta.todoapp.domain.dto.TodoResponse
import com.teamsparta.todoapp.domain.dto.UpdateCompleteTodoRequest
import com.teamsparta.todoapp.domain.dto.UpdateTodoRequest

interface TodoService {

    fun getTodoList(): List<TodoResponse>

    fun getTodo(todoId: Long): TodoResponse

    fun createTodo(request: CreateTodoRequest): TodoResponse

    fun updateTodo(todoId: Long, request: UpdateTodoRequest): TodoResponse

    fun deleteTodo(todoId: Long)

    fun updateCompleteTodo(todoId: Long, request: UpdateCompleteTodoRequest): TodoResponse
}