package com.example.synhub.tasks.model.response

import com.example.synhub.tasks.application.dto.EditTaskRequest
import com.example.synhub.tasks.application.dto.TaskRequest
import com.example.synhub.tasks.application.dto.TaskResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
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

    @PUT("tasks/{taskId}")
    suspend fun updateTask(@Path("taskId") taskId: Long, @Body editTaskRequest: EditTaskRequest): Response<TaskResponse>

    @PUT("tasks/{taskId}/status/{status}")
    suspend fun updateTaskStatus(@Path("taskId") taskId: Long?, @Path("status") status: String): Response<TaskResponse>
}