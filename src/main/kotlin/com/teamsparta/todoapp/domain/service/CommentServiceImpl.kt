package com.teamsparta.todoapp.domain.service

import com.teamsparta.todoapp.domain.dto.CommentRequest
import com.teamsparta.todoapp.domain.dto.CommentResponse
import com.teamsparta.todoapp.domain.exception.ModelNotFoundException
import com.teamsparta.todoapp.domain.model.Comment
import com.teamsparta.todoapp.domain.model.toResponse
import com.teamsparta.todoapp.domain.repository.CommentRepository
import com.teamsparta.todoapp.domain.repository.TodoRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CommentServiceImpl(
    private val commentRepository: CommentRepository,
    private val todoRepository: TodoRepository
) : CommentService {
    override fun getCommentList(todoId: Long): List<CommentResponse> {
        return commentRepository.findAll().map { it.toResponse() }
    }

    override fun submitComment(todoId: Long, request: CommentRequest): CommentResponse {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("todo", todoId)

        val comment = Comment(
            nickname = request.nickname,
            content = request.content,
            password = request.password,
            todo = todo
        )
        comment.addComment(comment)
        return comment.toResponse()
    }

    override fun updateComment(todoId: Long, commentId: Long, request: CommentRequest): CommentResponse {
        TODO("Not yet implemented")
    }

    override fun deleteComment(todoId: Long, commentId: Long, request: CommentRequest): CommentResponse {
        TODO("Not yet implemented")
    }
}