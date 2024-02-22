package com.mecheka.todo.route

import com.mecheka.todo.plugins.controllerComponent
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route

fun Route.jsonHolderRoute() {
    val controller = controllerComponent.postController()
    route("/json") {
        get("/todo") {
            val response = controller.getAllPost()
            call.respond(response)
        }
    }
}