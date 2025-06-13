package com.example.synhub.analytics.model.response

import com.example.synhub.analytics.application.dto.AnalyticsResponse
import com.example.synhub.analytics.application.dto.GroupMemberCountResponse
import com.example.synhub.analytics.application.dto.TaskTimePassedResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AnalyticsWebService {
    @GET("metrics/tasks/overview")
    suspend fun getTaskOverview(): Response<AnalyticsResponse>

    @GET("metrics/tasks/distribution")
    suspend fun getTaskDistribution(): Response<AnalyticsResponse>

    @GET("metrics/tasks/rescheduled")
    suspend fun getRescheduledTasks(): Response<AnalyticsResponse>

    @GET("metrics/tasks/avg-completion-time")
    suspend fun getAvgCompletionTime(): Response<AnalyticsResponse>

    @GET("metrics/task/member/{memberId}/time-passed")
    suspend fun getTaskTimePassed(@Path("memberId") memberId: Long): Response<TaskTimePassedResponse>
}
