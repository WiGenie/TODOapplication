package com.teamsparta.todoapp.domain.service

import com.teamsparta.todoapp.domain.dto.CreateTodoRequest
import com.teamsparta.todoapp.domain.dto.TodoResponse
import com.teamsparta.todoapp.domain.dto.UpdateTodoResponse
import org.springframework.stereotype.Service

@Service
class TodoServiceImpl(
//        private val todoRepository: TodoRepository,
): TodoService {

    override fun getTodoList(): List<TodoResponse> {
        TODO("Not yet implemented")
    }

    override fun getTodo(todoId: Long): TodoResponse {
        TODO("Not yet implemented")
    }

    override fun createTodo(request: CreateTodoRequest): TodoResponse {
        TODO("Not yet implemented")
    }

    override fun updateTodo(todoId: Long, request: UpdateTodoResponse): TodoResponse {
        TODO("Not yet implemented")
    }

    override fun deleteTodo(todoId: Long) {
        TODO("Not yet implemented")
    }

}