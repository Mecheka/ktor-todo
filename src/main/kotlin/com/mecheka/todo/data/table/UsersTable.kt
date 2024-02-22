package com.mecheka.todo.data.table

import org.jetbrains.exposed.dao.id.UUIDTable

object UsersTable : UUIDTable() {
    val username = varchar("username", length = 50)
    val password = text("password")
}