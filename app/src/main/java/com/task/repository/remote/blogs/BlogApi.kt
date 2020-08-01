package com.task.repository.remote.blogs

import com.task.repository.remote.blogs.models.BlogNetworkEntity
import retrofit2.http.GET

interface BlogApi {

    @GET("facts.json")
    suspend fun getBlogs(): BlogNetworkEntity
}