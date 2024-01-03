package com.teamsparta.todoapp.domain.service

import com.teamsparta.todoapp.domain.dto.CommentRequest
import com.teamsparta.todoapp.domain.dto.CommentResponse

class CommentServiceImpl(

):CommentService {
    override fun getCommentList(todoId: Long): List<CommentResponse> {
        TODO("Not yet implemented")
    }

    override fun submitComment(todoId: Long, request: CommentRequest): CommentResponse {
        TODO("Not yet implemented")
    }

    override fun updateComment(todoId: Long, request: CommentRequest, commentId: Long): CommentResponse {
        TODO("Not yet implemented")
    }

    override fun deleteComment(todoId: Long, request: CommentRequest, commentId: Long): CommentResponse {
        TODO("Not yet implemented")
    }
}