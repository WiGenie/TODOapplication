package com.teamsparta.todoapp.domain.service

import com.teamsparta.todoapp.domain.dto.CreateTodoRequest
import com.teamsparta.todoapp.domain.dto.TodoResponse
import com.teamsparta.todoapp.domain.dto.UpdateTodoResponse

interface TodoService {

    fun getTodoList(): List<TodoResponse>

    fun getTodo(todoId: Long): TodoResponse

    fun createTodo(request: CreateTodoRequest): TodoResponse

    fun updateTodo(todoId: Long, request: UpdateTodoResponse): TodoResponse

    fun deleteTodo(todoId: Long)
}