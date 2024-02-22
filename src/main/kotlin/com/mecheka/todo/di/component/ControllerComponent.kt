package com.mecheka.todo.di.component

import com.mecheka.todo.controller.AuthController
import com.mecheka.todo.controller.JwtController
import com.mecheka.todo.controller.PostController
import com.mecheka.todo.di.module.ControllerModule
import com.mecheka.todo.di.module.NetworkModule
import com.mecheka.todo.di.module.RepositoryModule
import dagger.Subcomponent
import javax.inject.Singleton

@Singleton
@Subcomponent(modules = [ControllerModule::class, RepositoryModule::class, NetworkModule::class])
interface ControllerComponent {
    fun jwtController(): JwtController
    fun authController(): AuthController
    fun postController(): PostController
}