package com.example.synhub.tasks.model.response

import com.example.synhub.tasks.application.dto.TaskResponse
import retrofit2.Response
import retrofit2.http.GET

interface TasksWebService {
    @GET("groups/tasks")
    suspend fun getGroupTasks(): Response<List<TaskResponse>>
}