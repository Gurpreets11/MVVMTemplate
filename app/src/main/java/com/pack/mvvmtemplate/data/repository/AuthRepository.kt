package com.pack.mvvmtemplate.data.repository

import com.pack.mvvmtemplate.data.model.AuthModels


interface AuthRepository {
    suspend fun login(request: AuthModels.LoginRequest): Result<AuthModels.LoginResponse>
    suspend fun register(request: AuthModels.RegisterRequest): Result<AuthModels.RegisterResponse>
    suspend fun verifyOtp(request: AuthModels.OtpRequest): Result<AuthModels.OtpResponse>
    suspend fun changePassword(request: AuthModels.ChangePasswordRequest): Result<AuthModels.ChangePasswordResponse>
}
