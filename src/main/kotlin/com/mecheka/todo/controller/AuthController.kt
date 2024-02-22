package com.mecheka.todo.controller

import com.mecheka.todo.data.repository.UserRepository
import kotlinx.serialization.Serializable
import javax.inject.Inject

interface AuthController {
    suspend fun register(username: String, password: String): AuthResponse
    suspend fun login(username: String, password: String): AuthResponse
}

class AuthControllerImpl @Inject constructor(
    private val userRepository: UserRepository,
    private val jwt: JwtController
) : AuthController {
    override suspend fun register(username: String, password: String): AuthResponse {
        return userRepository.addUser(username, password)?.let {
            val token = jwt.sign(it)
            AuthResponse.Success(token.token, token.expired.time)
        } ?: AuthResponse.Error
    }

    override suspend fun login(username: String, password: String): AuthResponse {
        return userRepository.findByUsernameAndPassword(username, password)?.let { user ->
            val token = jwt.sign(user.id)
            AuthResponse.Success(token.token, token.expired.time)
        } ?: AuthResponse.Error
    }
}

@Serializable
sealed class AuthResponse(open val message: String) {
    @Serializable
    data class Success(val token: String, val expired: Long) : AuthResponse("Success")
    data object Error : AuthResponse("Error")
}