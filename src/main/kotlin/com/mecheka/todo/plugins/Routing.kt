package com.mecheka.todo.plugins

import com.mecheka.todo.route.authApi
import com.mecheka.todo.route.jsonHolderRoute
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respondText
import io.ktor.server.routing.routing

fun Application.configureRouting() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
        }
    }
    routing {
        authApi(application.appComponent.controllerComponent().authController())
        jsonHolderRoute()
    }
}