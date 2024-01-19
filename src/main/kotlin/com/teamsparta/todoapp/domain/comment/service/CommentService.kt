package com.teamsparta.todoapp.domain.comment.service

import com.teamsparta.todoapp.domain.comment.dto.CommentRequest
import com.teamsparta.todoapp.domain.comment.dto.CommentResponse

interface CommentService {

    fun getCommentList(todoId: Long): List<CommentResponse>

    fun submitComment(todoId: Long, request: CommentRequest): CommentResponse

    fun updateComment(todoId: Long, commentId: Long, request: CommentRequest): CommentResponse

    fun deleteComment(todoId: Long, commentId: Long, request: CommentRequest): String
}
