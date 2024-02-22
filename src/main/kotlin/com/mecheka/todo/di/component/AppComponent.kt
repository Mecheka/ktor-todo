package com.mecheka.todo.di.component

import dagger.BindsInstance
import dagger.Component
import io.ktor.server.application.Application

@Component
interface AppComponent {

    fun controllerComponent(): ControllerComponent

    fun repositoryComponent(): RepositoryComponent

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun withApplication(application: Application): Builder

        fun build(): AppComponent
    }
}