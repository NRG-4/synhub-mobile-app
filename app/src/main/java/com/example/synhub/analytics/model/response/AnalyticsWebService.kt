package com.example.synhub.analytics.model.response

import com.example.synhub.analytics.application.dto.AnalyticsResponse
import com.example.synhub.analytics.application.dto.GroupMemberCountResponse
import com.example.synhub.analytics.application.dto.TaskTimePassedResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AnalyticsWebService {
    @GET("metrics/tasks/overview/{groupId}")
    suspend fun getTaskOverview(@Path("groupId") groupId: Long): Response<AnalyticsResponse>

    @GET("metrics/tasks/distribution/{groupId}")
    suspend fun getTaskDistribution(@Path("groupId") groupId: Long): Response<AnalyticsResponse>

    @GET("metrics/tasks/rescheduled/{groupId}")
    suspend fun getRescheduledTasks(@Path("groupId") groupId: Long): Response<AnalyticsResponse>

    @GET("metrics/tasks/avg-solution-time/{leaderId}")
    suspend fun getAvgSolutionTime(@Path("leaderId") leaderId: Long): Response<AnalyticsResponse>

    @GET("metrics/tasks/avg-dev-time/{memberId}")
    suspend fun getAvgDevTime(@Path("memberId") memberId: Long): Response<AnalyticsResponse>

    @GET("metrics/task/{taskId}/time-passed")
    suspend fun getTaskTimePassed(@Path("taskId") taskId: Long): Response<TaskTimePassedResponse>

    @GET("metrics/groups/{groupId}/member-count")
    suspend fun getGroupMemberCount(@Path("groupId") groupId: Long): Response<GroupMemberCountResponse>
}
