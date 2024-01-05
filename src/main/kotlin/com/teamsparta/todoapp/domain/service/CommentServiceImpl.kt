package com.teamsparta.todoapp.domain.service

import com.teamsparta.todoapp.domain.dto.CommentRequest
import com.teamsparta.todoapp.domain.dto.CommentResponse
import com.teamsparta.todoapp.domain.exception.ModelNotFoundException
import com.teamsparta.todoapp.domain.model.Comment
import com.teamsparta.todoapp.domain.model.toResponse
import com.teamsparta.todoapp.domain.repository.CommentRepository
import com.teamsparta.todoapp.domain.repository.TodoRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.ui.Model

@Service
class CommentServiceImpl(
    private val commentRepository: CommentRepository,
    private val todoRepository: TodoRepository
) : CommentService {
    override fun getCommentList(todoId: Long): List<CommentResponse> {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("todo", todoId)
        return todo.comments.map { it.toResponse() }
    }

    @Transactional
    override fun submitComment(todoId: Long, request: CommentRequest): CommentResponse {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("todo", todoId)

        val comment = Comment(
            nickname = request.nickname,
            content = request.content,
            password = request.password,
            todo = todo
        )
        todo.addComment(comment)
        todoRepository.save(todo)

        return comment.toResponse()
    }

    @Transactional
    override fun updateComment(todoId: Long, commentId: Long, request: CommentRequest): CommentResponse {
        val comment = commentRepository.findByTodoIdAndId(todoId, commentId) ?: throw ModelNotFoundException("comment", commentId)

        if (request.nickname == comment.nickname && request.password == comment.password) {
            comment.content = request.content

            return commentRepository.save(comment).toResponse()
        } else {
            throw IllegalStateException("nickname or password is not corrected. please check this.")
        }
    }

    @Transactional
    override fun deleteComment(todoId: Long, commentId: Long, request: CommentRequest):String {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("todo", todoId)
        val comment = commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException("comment", commentId)

        if (request.nickname == comment.nickname && request.password == comment.password) {
            todo.removeComment(comment)

            todoRepository.save(todo)
            return "delete Success"
        } else {
            throw IllegalStateException("nickname or password is not corrected. please check this.")
        }

    }
}