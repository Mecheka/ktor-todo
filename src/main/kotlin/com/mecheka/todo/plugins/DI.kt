package com.mecheka.todo.plugins

import com.mecheka.todo.di.component.AppComponent
import com.mecheka.todo.di.component.ControllerComponent
import com.mecheka.todo.di.component.DaggerAppComponent
import io.ktor.server.application.Application
import io.ktor.server.routing.Route
import io.ktor.server.routing.application
import io.ktor.util.AttributeKey

fun Application.configureDI() {
    val appComponent = DaggerAppComponent.builder().withApplication(this).build()
    val controllerComponent = appComponent.controllerComponent()

    attributes.put(appComponentKey, appComponent)
    attributes.put(controllerComponentKey, controllerComponent)
}

internal val appComponentKey = AttributeKey<AppComponent>("TODO_APP_COMPONENT")
internal val controllerComponentKey = AttributeKey<ControllerComponent>("CONTROLLER_COMPONENT")

val Application.appComponent: AppComponent get() = attributes[appComponentKey]
val Route.controllerComponent: ControllerComponent get() = this.application.attributes[controllerComponentKey]