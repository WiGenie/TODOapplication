package com.teamsparta.todoapp.domain.repository

import com.teamsparta.todoapp.domain.model.TodoApp
import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository: JpaRepository<TodoApp, Long>{
//모델에 TodoApp을 만들어야 여기서 타입을 TodoApp으로 지정 가능!
    // 앞에는 entity의 타입을 지정해줘야 함 (여기서는 TodoApp)
    // 뒤에는 id    의 타입을 지정해줘야 함 (여기서는 Long)
}