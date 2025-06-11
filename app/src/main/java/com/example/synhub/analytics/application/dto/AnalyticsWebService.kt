package com.example.synhub.analytics.application.dto

import com.example.synhub.analytics.model.response.GroupMemberCountResource
import com.example.synhub.analytics.model.response.MetricResource
import com.example.synhub.analytics.model.response.TaskTimePassedResource
import retrofit2.http.GET
import retrofit2.http.Path

interface AnalyticsWebService {
    @GET("api/v1/metrics/tasks/overview/{groupId}")
    suspend fun getTaskOverview(@Path("groupId") groupId: Long): MetricResource

    @GET("api/v1/metrics/tasks/distribution/{groupId}")
    suspend fun getTaskDistribution(@Path("groupId") groupId: Long): MetricResource

    @GET("api/v1/metrics/tasks/rescheduled/{groupId}")
    suspend fun getRescheduledTasks(@Path("groupId") groupId: Long): MetricResource

    @GET("api/v1/metrics/tasks/avg-solution-time/{leaderId}")
    suspend fun getAvgSolutionTime(@Path("leaderId") leaderId: Long): MetricResource

    @GET("api/v1/metrics/tasks/avg-dev-time/{memberId}")
    suspend fun getAvgDevTime(@Path("memberId") memberId: Long): MetricResource

    @GET("api/v1/metrics/task/{taskId}/time-passed")
    suspend fun getTaskTimePassed(@Path("taskId") taskId: Long): TaskTimePassedResource

    @GET("api/v1/metrics/groups/{groupId}/member-count")
    suspend fun getGroupMemberCount(@Path("groupId") groupId: Long): GroupMemberCountResource
}
