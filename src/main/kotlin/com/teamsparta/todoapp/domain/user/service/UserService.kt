package com.teamsparta.todoapp.domain.user.service

import com.teamsparta.todoapp.domain.user.dto.SigninResponse
import com.teamsparta.todoapp.domain.user.dto.UserRequest
import com.teamsparta.todoapp.domain.user.dto.UserResponse

interface UserService {

    fun signUp(request: UserRequest): UserResponse

    fun signIn(request: UserRequest): SigninResponse
}