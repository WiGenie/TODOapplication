package com.teamsparta.todoapp.domain.todo.controller

import com.teamsparta.todoapp.domain.todo.dto.CreateTodoRequest
import com.teamsparta.todoapp.domain.todo.dto.TodoResponse
import com.teamsparta.todoapp.domain.todo.dto.CompleteTodoRequest
import com.teamsparta.todoapp.domain.todo.dto.UpdateTodoRequest
import com.teamsparta.todoapp.domain.todo.service.TodoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RequestMapping("/todos")
@RestController
class TodoController(
    private val todoService: TodoService
) {

    @GetMapping()
    fun getTodoList(): ResponseEntity<List<TodoResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.getTodoList())
    }

    @GetMapping("/{todoId}")
    fun getTodo(@PathVariable todoId: Long): ResponseEntity<TodoResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.getTodo(todoId))
    }

    @PostMapping
    fun createTodo(@RequestBody createTodoRequest: CreateTodoRequest): ResponseEntity<TodoResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(todoService.createTodo(createTodoRequest))
    }

    @PutMapping("/{todoId}")
    fun updateTodo(
        @PathVariable todoId: Long,
        @RequestBody updateTodoRequest: UpdateTodoRequest
    ): ResponseEntity<TodoResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.updateTodo(todoId, updateTodoRequest))
    }

    @DeleteMapping("/{todoId}")
    fun deleteTodo(@PathVariable todoId: Long,
                   @RequestBody updateTodoRequest: UpdateTodoRequest
    ): ResponseEntity<Unit> {
        todoService.deleteTodo(todoId, updateTodoRequest)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }

    @PatchMapping("/{todoId}")
    fun updateCompleteTodo(
        @PathVariable todoId: Long,
        @RequestBody completeTodoRequest: CompleteTodoRequest
    ): ResponseEntity<TodoResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(
                todoService.updateCompleteTodo(
                    todoId,
                    completeTodoRequest
                )
            )
    }
}