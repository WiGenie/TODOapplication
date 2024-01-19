package com.teamsparta.todoapp.domain.todo.service

import com.teamsparta.todoapp.domain.todo.dto.CreateTodoRequest
import com.teamsparta.todoapp.domain.todo.dto.TodoResponse
import com.teamsparta.todoapp.domain.todo.dto.CompleteTodoRequest
import com.teamsparta.todoapp.domain.todo.dto.UpdateTodoRequest

interface TodoService {

    fun getTodoList(): List<TodoResponse>

    fun getTodo(todoId: Long): TodoResponse

    fun createTodo(request: CreateTodoRequest): TodoResponse

    fun updateTodo(todoId: Long, request: UpdateTodoRequest): TodoResponse

    fun deleteTodo(todoId: Long, request: UpdateTodoRequest)

    fun updateCompleteTodo(todoId: Long, request: CompleteTodoRequest): TodoResponse
}