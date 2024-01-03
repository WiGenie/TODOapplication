package com.teamsparta.todoapp.domain.service

import com.teamsparta.todoapp.domain.dto.CommentRequest
import com.teamsparta.todoapp.domain.dto.CommentResponse

interface CommentService {

    fun getCommentList(todoId: Long): List<CommentResponse>

    fun submitComment(todoId: Long, request: CommentRequest): CommentResponse

    fun updateComment(todoId: Long, request: CommentRequest, commentId: Long): CommentResponse

    fun deleteComment(todoId: Long, request: CommentRequest,commentId: Long): CommentResponse
}
