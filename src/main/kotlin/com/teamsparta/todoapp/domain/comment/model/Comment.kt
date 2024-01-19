package com.teamsparta.todoapp.domain.comment.model

import com.teamsparta.todoapp.domain.comment.dto.CommentResponse
import com.teamsparta.todoapp.domain.todo.model.TodoApp
import com.teamsparta.todoapp.domain.user.model.User
import jakarta.persistence.*

@Entity
@Table(name = "todocomment")
class Comment(

    @Column(name = "nickname", nullable = false)
    var nickname: String,

    @Column(name = "content", nullable = false)
    var content: String,

//    @Column(name = "password", nullable = false)
//    var password: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id")
    var todo: TodoApp,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User

//    TodoApp에 아무것도 작성하지 않았으면(@onetomany)
//    다대일 단방향 (삭제 문제로 양방향으로 바꿨음...)
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

}

fun Comment.toResponse(): CommentResponse {
    return CommentResponse(
        id = id!!,
        nickname = nickname,
        content = content
    )
}