package com.mecheka.todo.di.component

import com.mecheka.todo.data.repository.UserRepository
import com.mecheka.todo.di.module.RepositoryModule
import dagger.Subcomponent

@Subcomponent(modules = [RepositoryModule::class])
interface RepositoryComponent {
    fun userRepository(): UserRepository
}