package com.teamsparta.todoapp.domain.service

import com.teamsparta.todoapp.domain.dto.CreateTodoRequest
import com.teamsparta.todoapp.domain.dto.TodoResponse
import com.teamsparta.todoapp.domain.dto.UpdateCompleteTodoRequest
import com.teamsparta.todoapp.domain.dto.UpdateTodoRequest
import com.teamsparta.todoapp.domain.exception.ModelNotFoundException
import com.teamsparta.todoapp.domain.model.CompleteTodo
import com.teamsparta.todoapp.domain.model.TodoApp
import com.teamsparta.todoapp.domain.model.toResponse
import com.teamsparta.todoapp.domain.repository.TodoRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.format.DateTimeFormatter

@Service
class TodoServiceImpl(
    private val todoRepository: TodoRepository,
) : TodoService {

    override fun getTodoList(): List<TodoResponse> {
//        val todoResponse = todoRepository.findAll()
//        return todoResponse.map { todo ->
//            TodoResponse(
//                id= todo.id!!,
//                title = todo.title,
//                detail = todo.detail,
//                date = todo.date,
//                name = todo.name
//            )
//        }
        return todoRepository.findAll().map { it.toResponse() }
        // it.toResponse() 만으로 TodoApp.toResponse()가 기능한 이유는
        // it의 값이 모두 todoApp의 값이므로 todoApp의 확장함수로써 기능함
    }

    override fun getTodo(todoId: Long): TodoResponse {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)
//        val todoResponse= TodoResponse(
//            id= todo.id!!,
//            title = todo.title,
//            detail = todo.detail,
//            date = todo.date,
//            name = todo.name
//        )
//        return todoResponse
        return todo.toResponse()
    }

    @Transactional
    override fun createTodo(request: CreateTodoRequest): TodoResponse {
        return todoRepository.save(
            TodoApp(
                title = request.title,
                detail = request.detail,
                name = request.name,
                complete= CompleteTodo.FALSE,
            )
        ).toResponse()
    }

    @Transactional
    override fun updateTodo(todoId: Long, request: UpdateTodoRequest): TodoResponse {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)
        val (title, name, detail)= request
        todo.title= title
        todo.name= name
        todo.detail= detail

        return todoRepository.save(todo).toResponse()

    }

    @Transactional
    override fun deleteTodo(todoId: Long) {
        val todo= todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("todo", todoId)
        todoRepository.delete(todo)
    }

    @Transactional
    override fun updateCompleteTodo(
        todoId: Long,
        request: UpdateCompleteTodoRequest
    ): TodoResponse {
        val todo= todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("todo", todoId)
        todo.isCompleted()
        return todoRepository.save(todo).toResponse()
    }

}