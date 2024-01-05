package com.teamsparta.todoapp.domain.repository

import com.teamsparta.todoapp.domain.model.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository:JpaRepository<Comment, Long> {

    fun findByTodoIdAndId(todoId: Long, commentId: Long): Comment?

}