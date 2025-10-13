package com.pack.mvvmtemplate.data.model

class AuthModels {
    data class LoginRequest(val email: String, val password: String)
    data class LoginResponse(val token: String, val userName: String?)

}