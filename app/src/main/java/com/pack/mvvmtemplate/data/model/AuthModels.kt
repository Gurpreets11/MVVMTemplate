package com.pack.mvvmtemplate.data.model

class AuthModels {
    data class LoginRequest(val email: String, val password: String)


    data class RegisterRequest(
        val email: String,
        val name: String,
        val mobileNumber: String,
        val password: String
    )

    data class OtpRequest(val otp: String)
    data class ChangePasswordRequest(  val password: String, val newPassword: String)


    data class LoginResponse(val token: String, val userName: String?)
    data class RegisterResponse(val token: String, val userName: String?)
    data class OtpResponse(val token: String, val userName: String?)
    data class ChangePasswordResponse(val token: String, val userName: String?)
}