package com.teamsparta.todoapp.domain.controller

import com.teamsparta.todoapp.domain.dto.CreateTodoRequest
import com.teamsparta.todoapp.domain.dto.TodoResponse
import com.teamsparta.todoapp.domain.dto.UpdateCompleteTodoRequest
import com.teamsparta.todoapp.domain.dto.UpdateTodoRequest
import com.teamsparta.todoapp.domain.service.TodoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


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
    fun getTodo(@PathVariable todoId: Long): ResponseEntity<TodoResponse>{
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(todoService.getTodo(todoId))
    }

    @PostMapping
    fun createTodo(@RequestBody createTodoRequest: CreateTodoRequest): ResponseEntity<TodoResponse>{
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(todoService.createTodo(createTodoRequest))
    }

    @PutMapping("/{todoId}")
    fun updateTodo(@PathVariable todoId: Long, @RequestBody updateTodoRequest: UpdateTodoRequest): ResponseEntity<TodoResponse>{
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(todoService.updateTodo(todoId, updateTodoRequest))
    }

    @DeleteMapping("/{todoId}")
    fun deleteTodo(@PathVariable todoId: Long): ResponseEntity<Unit>{
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(todoService.deleteTodo(todoId))
    }

    @PatchMapping("/{todoId}")
    fun updateCompleteTodo(
        @PathVariable todoId: Long,
        @RequestBody updateCompleteTodoRequest: UpdateCompleteTodoRequest
    ): ResponseEntity<TodoResponse>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(
                todoService.updateCompleteTodo(
                    todoId,
                    updateCompleteTodoRequest
                )
            )
    }
}