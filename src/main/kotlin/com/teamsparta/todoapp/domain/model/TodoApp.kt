package com.teamsparta.todoapp.domain.model

import com.teamsparta.todoapp.domain.dto.TodoResponse
import jakarta.persistence.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Entity
@Table(name= "todo")
class TodoApp(

    @Column(name = "name", nullable= false)
    var name: String,

    @Column(name= "title", nullable= false)
    var title: String,

    @Column(name = "date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    var date: LocalDateTime= LocalDateTime.now(),

    @Column(name= "detail")
    var detail: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "complete", nullable = false)
    var complete: CompleteTodo = CompleteTodo.FALSE,

    @OneToMany(mappedBy = "todo", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    var comments: MutableList<Comment> = mutableListOf(),
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    fun isCompleted(){
        complete = CompleteTodo.TRUE
    }

    fun addComment(comment: Comment){
        comments.add(comment)
    }
}

fun TodoApp.toResponse(): TodoResponse{
    return TodoResponse(
        id = id!!,
        title = title,
        detail = detail,
        date = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
        name = name,
        complete = complete.name
    )
} // 일종의 확장함수 개념