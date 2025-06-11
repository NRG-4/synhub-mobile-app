package com.example.synhub.analytics.data

data class MetricResource(
    val type: String?,
    val value: Double?, // Cambiado de Int? a Double?
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
