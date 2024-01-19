package com.teamsparta.todoapp.domain.comment.controller

import com.teamsparta.todoapp.domain.comment.dto.CommentRequest
import com.teamsparta.todoapp.domain.comment.dto.CommentResponse
import com.teamsparta.todoapp.domain.comment.service.CommentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RequestMapping("/todos/{todoId}/comments")
@RestController
class CommentController(
    private val commentService: CommentService
) {

    @GetMapping
    fun getCommentList(@PathVariable todoId: Long): ResponseEntity<List<CommentResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.getCommentList(todoId))
    }

    @PostMapping
    fun submitComment(
        @PathVariable todoId: Long,
        @RequestBody submitCommentRequest: CommentRequest
    ): ResponseEntity<CommentResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(commentService.submitComment(todoId, submitCommentRequest))
    }

    @PatchMapping("/{commentId}")
    fun updateComment(
        @PathVariable todoId: Long,
        @PathVariable commentId: Long,
        @RequestBody updateCommentRequest: CommentRequest,
    ): ResponseEntity<CommentResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.updateComment(todoId, commentId, updateCommentRequest))
    }

    @DeleteMapping("/{commentId}")
    fun deleteComment(
        @PathVariable todoId: Long,
        @PathVariable commentId: Long,
        @RequestBody deleteCommentRequest: CommentRequest,
    ): ResponseEntity<String> {
        val result = commentService.deleteComment(todoId, commentId, deleteCommentRequest)
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(result)
    }

}