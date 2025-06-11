package com.example.synhub.analytics.model.response

data class MetricResource(
    val type: String?,
    val value: Double?,
    val details: Map<String, Any>?,
    val context: String?,
    val summary: String?
)

data class TaskTimePassedResource(
    val taskId: Long?,
    val timePassed: Long?
)

data class GroupMemberCountResource(
    val groupId: Long?,
    val memberCount: Int?
)
