package com.teamsparta.todoapp.domain.user.model

import com.teamsparta.todoapp.domain.comment.model.Comment
import com.teamsparta.todoapp.domain.todo.model.TodoApp
import com.teamsparta.todoapp.domain.user.dto.UserResponse
import jakarta.persistence.*

@Entity
@Table(name = "app_user")
class User(

    @Column(name = "email", nullable = false)
    val email: String,

    @Column(name = "password", nullable = false)
    var password: String,

    @Column(name = "nickname", nullable = false)
    var nickname: String,

    @Column(name = "role", nullable = false)
    var role: String,

    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    var posts: MutableList<TodoApp> = mutableListOf(),

    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    var comments: MutableList<Comment> = mutableListOf(),

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}

fun User.toResponse(): UserResponse {
    return UserResponse (
        id = id!!,
        nickname = nickname,
        email = email,
        role = role
    )
}