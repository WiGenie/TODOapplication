package com.teamsparta.todoapp.domain.comment.service

import com.teamsparta.todoapp.domain.comment.dto.CommentRequest
import com.teamsparta.todoapp.domain.comment.dto.CommentResponse
import com.teamsparta.todoapp.domain.comment.model.Comment
import com.teamsparta.todoapp.domain.comment.model.toResponse
import com.teamsparta.todoapp.domain.comment.repository.CommentRepository
import com.teamsparta.todoapp.domain.todo.exception.ModelNotFoundException
import com.teamsparta.todoapp.domain.todo.repository.TodoRepository
import com.teamsparta.todoapp.domain.user.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CommentServiceImpl(
    private val commentRepository: CommentRepository,
    private val todoRepository: TodoRepository,
    private val userRepository: UserRepository,
) : CommentService {
    override fun getCommentList(todoId: Long): List<CommentResponse> {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("todo", todoId)
        return todo.comments.map { it.toResponse() }
    }

    @Transactional
    override fun submitComment(todoId: Long, request: CommentRequest): CommentResponse {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("todo", todoId)
        val user = userRepository.findByIdOrNull(request.userId) ?: throw ModelNotFoundException("User", null)

        val comment = Comment(
            nickname = user.nickname,
            content = request.content,
            todo = todo,
            user = user,
        )
        todo.addComment(comment)
        todoRepository.save(todo)

        return comment.toResponse()
    }

    @Transactional
    override fun updateComment(todoId: Long, commentId: Long, request: CommentRequest): CommentResponse {
        val comment = commentRepository.findByTodoIdAndId(todoId, commentId)
                ?: throw ModelNotFoundException("comment", commentId)

        val user = userRepository.findByIdOrNull(request.userId)
            ?: throw ModelNotFoundException("User", null)


        if (comment.user == user) {
            comment.content = request.content

            return commentRepository.save(comment).toResponse()
        } else {
            throw IllegalStateException("작성자만 수정 가능")
        }
    }

    @Transactional
    override fun deleteComment(todoId: Long, commentId: Long, request: CommentRequest): String {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("todo", todoId)
        val comment = commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException("comment", commentId)
        val user = userRepository.findByIdOrNull(request.userId) ?: throw ModelNotFoundException("User", null)

        if (comment.user == user) {
            todo.removeComment(comment)

            todoRepository.save(todo)
            return "delete Success"
        } else {
            throw IllegalStateException("작성자만 삭제 가능")
        }

    }
}