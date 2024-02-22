package com.mecheka.todo.controller

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import java.util.Date
import javax.inject.Inject


interface JwtController {
    val verifier: JWTVerifier

    fun sign(data: String): JsonTokenResult
}

class JwtControllerImpl @Inject constructor() : JwtController {
    private val algorithm = Algorithm.HMAC256("secret")
    override val verifier: JWTVerifier = JWT
        .require(algorithm)
        .withIssuer(ISSUER)
        .withAudience(AUDIENCE)
        .build()

    override fun sign(data: String): JsonTokenResult {
        val expired = Date(System.currentTimeMillis() + 60000)
        val token = JWT
            .create()
            .withIssuer(ISSUER)
            .withAudience(AUDIENCE)
            .withClaim(ClAIM, data)
            .withExpiresAt(expired)
            .sign(algorithm)

        return JsonTokenResult(token, expired)
    }

    companion object {
        private const val ISSUER = "Todo-JWT-Issuer"
        private const val AUDIENCE = "https://Todo.app"
        const val ClAIM = "userId"
    }
}

data class JsonTokenResult(val token: String, val expired: Date)