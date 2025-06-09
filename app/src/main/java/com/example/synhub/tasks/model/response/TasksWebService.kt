package com.example.synhub.tasks.model.response

import com.example.synhub.tasks.application.dto.TaskRequest
import com.example.synhub.tasks.application.dto.TaskResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TasksWebService {
    @GET("groups/tasks")
    suspend fun getGroupTasks(): Response<List<TaskResponse>>

    @GET("tasks/{taskId}")
    suspend fun getTaskById(@Path("taskId") taskId: Long): Response<TaskResponse>

    @POST("members/{memberId}/tasks")
    suspend fun createTask(
        @Path("memberId") memberId: Long,
        @Body taskRequest: TaskRequest
    ): Response<TaskResponse>

    @DELETE("tasks/{taskId}")
    suspend fun deleteTask(@Path("taskId") taskId: Long): Response<Void>
}