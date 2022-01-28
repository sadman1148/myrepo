package com.redenvy.justdoit.data.network

import com.redenvy.justdoit.data.RemoteDB.TodoList
import retrofit2.http.GET

interface APIService {
    @GET(APIurl.EXTENSION_URL)
    suspend fun todoList(): TodoList
}