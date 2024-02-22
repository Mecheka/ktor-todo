package com.mecheka.todo.data.repository

import com.mecheka.todo.data.model.User
import com.mecheka.todo.data.table.UsersTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select
import java.util.UUID
import javax.inject.Inject

interface UserRepository {
    suspend fun addUser(username: String, password: String): String?
    suspend fun findById(uuid: UUID): User?
    suspend fun findByUsernameAndPassword(username: String, password: String): User?
}

class UserRepositoryImpl @Inject constructor() : UserRepository {
    override suspend fun addUser(username: String, password: String) = dbQuery {
        runCatching {
            val userid = UsersTable.insertAndGetId {
                it[UsersTable.username] = username
                it[UsersTable.password] = password
            }

            userid.value.toString()
        }.getOrNull()
    }

    override suspend fun findById(uuid: UUID): User? = dbQuery {
        UsersTable.select { UsersTable.id eq uuid }
            .map(::mapUserResult).firstOrNull()
    }

    override suspend fun findByUsernameAndPassword(username: String, password: String): User? = dbQuery {
        UsersTable.select { (UsersTable.username eq username) and (UsersTable.password eq password) }
            .map(::mapUserResult)
            .firstOrNull()
    }

    private fun mapUserResult(result: ResultRow) = User(
        id = result[UsersTable.id].value.toString(),
        username = result[UsersTable.username],
        password = result[UsersTable.password]
    )
}