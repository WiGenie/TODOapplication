package com.teamsparta.todoapp.domain.controller

import com.teamsparta.todoapp.domain.dto.CommentResponse
import com.teamsparta.todoapp.domain.service.CommentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RequestMapping("/todos/{todoid}/comments")
@RestController
class CommentController(
    private val commentService: CommentService
) {

    @GetMapping
    fun getCommentList(@PathVariable todoId: Long): ResponseEntity<List<CommentResponse>>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.getCommentList(todoId))
    }
}