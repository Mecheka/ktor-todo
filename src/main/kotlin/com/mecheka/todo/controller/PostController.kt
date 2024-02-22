package com.mecheka.todo.controller

import com.mecheka.todo.data.model.JsonHolderTodo
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

interface PostController {
    suspend fun getAllPost(): List<JsonHolderTodo>
}

class PostControllerImpl @Inject constructor(private val httpClient: HttpClient) : PostController {
    override suspend fun getAllPost(): List<JsonHolderTodo> {
        val response = httpClient.get("https://jsonplaceholder.typicode.com/todos").body<List<JsonHolderTodo>>()
        httpClient.close()
        return response
    }
}