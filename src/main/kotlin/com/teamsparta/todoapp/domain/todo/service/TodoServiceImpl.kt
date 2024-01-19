package com.teamsparta.todoapp.domain.todo.service

import com.teamsparta.todoapp.domain.todo.dto.CompleteTodoRequest
import com.teamsparta.todoapp.domain.todo.dto.CreateTodoRequest
import com.teamsparta.todoapp.domain.todo.dto.TodoResponse
import com.teamsparta.todoapp.domain.todo.dto.UpdateTodoRequest
import com.teamsparta.todoapp.domain.todo.exception.ModelNotFoundException
import com.teamsparta.todoapp.domain.todo.model.CompleteTodo
import com.teamsparta.todoapp.domain.todo.model.TodoApp
import com.teamsparta.todoapp.domain.todo.model.toResponse
import com.teamsparta.todoapp.domain.todo.repository.TodoRepository
import com.teamsparta.todoapp.domain.user.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TodoServiceImpl(
    private val todoRepository: TodoRepository,
    private val userRepository: UserRepository,
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
        val user = userRepository.findByIdOrNull(request.userId) ?: throw ModelNotFoundException("User", null)
        return todoRepository.save(
            TodoApp(
                user = user,
                title = request.title,
                detail = request.detail,
                name = user.nickname,
                complete = CompleteTodo.FALSE,
            )
        ).toResponse()
    }

    @Transactional
    override fun updateTodo(todoId: Long, request: UpdateTodoRequest): TodoResponse {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)
        val user = userRepository.findByIdOrNull(request.userId) ?: throw ModelNotFoundException("User", null)

        if (todo.user == user) {

            todo.title = request.title
            todo.name = user.nickname
            todo.detail = request.detail

            return todoRepository.save(todo).toResponse()
        } else {
            throw IllegalStateException("작성자만 수정 가능")
        }

    }

    @Transactional
    override fun deleteTodo(todoId: Long, request: UpdateTodoRequest) {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("todo", todoId)
        val user = userRepository.findByIdOrNull(request.userId) ?: throw ModelNotFoundException("User", null)

        if (todo.user == user) {
            todoRepository.delete(todo)
        }
        else{
            throw IllegalStateException("작성자만 삭제 가능")
        }
    }

    @Transactional
    override fun updateCompleteTodo(
        todoId: Long,
        request: CompleteTodoRequest
    ): TodoResponse {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("todo", todoId)
        val user = userRepository.findByIdOrNull(request.userId) ?: throw ModelNotFoundException("User", null)



        if (todo.user == user) {
            todo.isCompleted()
            return todoRepository.save(todo).toResponse()
        }
        else{
            throw IllegalStateException("작성자만 완료 가능")
        }
    }

}