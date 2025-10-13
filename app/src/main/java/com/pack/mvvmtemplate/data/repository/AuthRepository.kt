package com.pack.mvvmtemplate.data.repository

import com.pack.mvvmtemplate.data.model.AuthModels


interface AuthRepository {
    suspend fun login(request: AuthModels.LoginRequest): Result<AuthModels.LoginResponse>
    suspend fun register(/*...*/): Result<Unit>
    suspend fun verifyOtp(/*...*/): Result<Unit>
    suspend fun changePassword(/*...*/): Result<Unit>
}
