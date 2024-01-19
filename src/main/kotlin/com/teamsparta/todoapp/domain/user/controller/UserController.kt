package com.teamsparta.todoapp.domain.user.controller

import com.teamsparta.todoapp.domain.user.dto.SigninResponse
import com.teamsparta.todoapp.domain.user.dto.UserRequest
import com.teamsparta.todoapp.domain.user.dto.UserResponse
import com.teamsparta.todoapp.domain.user.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userService: UserService
) {

    @PostMapping("/signup")
    fun signUp(@RequestBody userrequest: UserRequest): ResponseEntity<UserResponse> {

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(userService.signUp(userrequest))
    }

    @PostMapping("/signin")
    fun signIn(@RequestBody userrequest: UserRequest): ResponseEntity<SigninResponse> {

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.signIn(userrequest))
    }

//    @PatchMapping("/users/{userId}/profile")
//    fun getUserProfile(@PathVariable userId: Long,
//                       @RequestBody userrequest: UserRequest
//    ): ResponseEntity<String>{
//
//        return ResponseEntity
//            .status(HttpStatus.OK)
//            .body("회원 정보가 변경되었습니다.")
//    }
}