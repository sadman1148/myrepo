package com.redenvy.justdoit.data.Remote.network

import com.redenvy.justdoit.data.Remote.TodoList
import retrofit2.http.GET

interface APIService {
    @GET(APIurl.EXTENSION_URL)
    suspend fun todoList(): TodoList
}