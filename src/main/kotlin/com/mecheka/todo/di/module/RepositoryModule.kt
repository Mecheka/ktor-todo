package com.mecheka.todo.di.module

import com.mecheka.todo.data.repository.UserRepository
import com.mecheka.todo.data.repository.UserRepositoryImpl
import dagger.Binds
import dagger.Module

@Module()
interface RepositoryModule {
    @Binds
    fun bindsUserRepository(repository: UserRepositoryImpl): UserRepository
}