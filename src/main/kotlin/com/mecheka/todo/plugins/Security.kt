package com.mecheka.todo.plugins

import com.mecheka.todo.controller.JwtController
import com.mecheka.todo.controller.JwtControllerImpl
import com.mecheka.todo.data.repository.UserRepository
import io.ktor.server.application.Application
import io.ktor.server.auth.Principal
import io.ktor.server.auth.authentication
import io.ktor.server.auth.jwt.jwt
import java.util.UUID

fun Application.configureSecurity(
    jwtController: JwtController = appComponent.controllerComponent().jwtController(),
    userRepository: UserRepository = appComponent.repositoryComponent().userRepository()
) {
    authentication {
        jwt {
            verifier(jwtController.verifier)
            validate { credential ->
                val userId = credential.payload.getClaim(JwtControllerImpl.ClAIM).asString()
                val user = userRepository.findById(UUID.fromString(userId))
                if (user != null) UserPrincipal(user.username) else null
            }
        }
    }
}


data class UserPrincipal(val username: String) : Principal