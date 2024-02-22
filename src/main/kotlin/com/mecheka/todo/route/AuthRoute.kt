package com.mecheka.todo.route

import com.mecheka.todo.controller.AuthController
import com.mecheka.todo.controller.AuthResponse
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receiveParameters
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import io.ktor.server.routing.route

fun Route.authApi(authController: AuthController) {
    route("/auth") {
        post("/register") {
            val request = call.receiveParameters()
            val username = request["username"].toString()
            val password = request["password"].toString()
            when (val result = authController.register(username, password)) {
                is AuthResponse.Error -> {
                    call.respond(status = HttpStatusCode.NotFound, message = result.message)
                }

                is AuthResponse.Success -> {
                    call.respond(result)
                }
            }
        }

        post("/login") {
            val request = call.receiveParameters()
            val username = request["username"].toString()
            val password = request["password"].toString()
            when (val result = authController.login(username, password)) {
                is AuthResponse.Error -> {
                    call.respond(status = HttpStatusCode.NotFound, message = result.message)
                }

                is AuthResponse.Success -> {
                    call.respond(result)
                }
            }
        }
    }
}
