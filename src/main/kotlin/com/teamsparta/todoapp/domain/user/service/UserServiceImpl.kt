package com.teamsparta.todoapp.domain.user.service

import com.teamsparta.todoapp.domain.todo.exception.InvalidCredentialException
import com.teamsparta.todoapp.domain.todo.exception.ModelNotFoundException
import com.teamsparta.todoapp.domain.user.dto.SigninResponse
import com.teamsparta.todoapp.domain.user.dto.UserRequest
import com.teamsparta.todoapp.domain.user.dto.UserResponse
import com.teamsparta.todoapp.domain.user.model.User
import com.teamsparta.todoapp.domain.user.model.toResponse
import com.teamsparta.todoapp.domain.user.repository.UserRepository
import com.teamsparta.todoapp.infra.security.jwt.JwtPlugin
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val jwtPlugin: JwtPlugin,
    private val passwordEncoder: PasswordEncoder,
) : UserService {

    @Transactional
    override fun signUp(request: UserRequest): UserResponse {
        if (userRepository.existsByEmail(request.email)) {
            throw IllegalStateException("이미 사용되고 있는 이메일입니다.")
        }
        return userRepository.save(
            User(
                email = request.email,
                password = passwordEncoder.encode(request.password),
                nickname = request.nickname,
                role = "USER",
            )
        ).toResponse()

    }

    override fun signIn(request: UserRequest): SigninResponse {
        val user = userRepository.findByEmail(request.email)
            ?: throw ModelNotFoundException("User", null)

        if (!passwordEncoder.matches(request.password, user.password)) {
            throw InvalidCredentialException()
        }

        return SigninResponse(
            accessToken = jwtPlugin.generateAccessToken(
                subject = user.id.toString(),
                email = user.email,
                role = user.role,
            )
        )
    }
}