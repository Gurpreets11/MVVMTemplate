package com.pack.mvvmtemplate.data.repository

import com.pack.mvvmtemplate.data.model.AuthModels
import kotlinx.coroutines.delay
import javax.inject.Inject

class FakeAuthRepository @Inject constructor() : AuthRepository {
    override suspend fun login(request: AuthModels.LoginRequest): Result<AuthModels.LoginResponse> {
        delay(800) // simulate network
        return if (request.email == "demo@demo.com" && request.password == "password") {
            Result.success(
                AuthModels.LoginResponse(
                    token = "fake_token_123",
                    userName = "Demo User"
                )
            )
        } else {
            Result.failure(Exception("Invalid credentials"))
        }
    }

    override suspend fun register(): Result<Unit> {
        delay(600)
        return Result.success(Unit)
    }

    override suspend fun verifyOtp(): Result<Unit> {
        delay(600)
        return Result.success(Unit)
    }

    override suspend fun changePassword(): Result<Unit> {
        delay(600)
        return Result.success(Unit)
    }
}
