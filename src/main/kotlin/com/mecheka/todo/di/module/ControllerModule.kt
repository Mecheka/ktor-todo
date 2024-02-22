package com.mecheka.todo.di.module

import com.mecheka.todo.controller.AuthController
import com.mecheka.todo.controller.AuthControllerImpl
import com.mecheka.todo.controller.JwtController
import com.mecheka.todo.controller.JwtControllerImpl
import com.mecheka.todo.controller.PostController
import com.mecheka.todo.controller.PostControllerImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface ControllerModule {

    @Singleton
    @Binds
    fun bindsJwtController(controller: JwtControllerImpl): JwtController

    @Binds
    fun bindsAuthController(controller: AuthControllerImpl): AuthController

    @Binds
    fun bindsPostController(controller: PostControllerImpl): PostController
}