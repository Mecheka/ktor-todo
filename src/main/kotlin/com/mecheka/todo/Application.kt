package com.mecheka.todo

import com.mecheka.todo.data.table.UsersTable
import com.mecheka.todo.plugins.configureDI
import com.mecheka.todo.plugins.configureRouting
import com.mecheka.todo.plugins.configureSecurity
import com.mecheka.todo.plugins.configureSerialization
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.application.Application
import io.ktor.server.netty.EngineMain
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    configureDI()
    configureSerialization()
    configureSecurity()
    configureRouting()
    initDatabase()
}

private fun initDatabase() {
    val config = HikariConfig().apply {
        jdbcUrl = "jdbc:postgresql://localhost:5432/todo"
        password = "root"
        username = "postgres"
        driverClassName = "org.postgresql.Driver"
    }

    val database = Database.connect(HikariDataSource(config))

    transaction(database) {
        SchemaUtils.create(UsersTable)
    }
}