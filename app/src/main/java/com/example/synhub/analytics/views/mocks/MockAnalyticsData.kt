package com.example.synhub.analytics.views.mocks

object MockAnalyticsData {

    fun getTaskOverview(groupId: Long) = mapOf(
        "type" to "TASK_OVERVIEW",
        "value" to 5,
        "details" to mapOf(
            "IN_PROGRESS" to 2,
            "COMPLETED" to 3
        ),
        "context" to "groupId=$groupId",
        "summary" to "Vista general de tareas: 5.00"
    )

    fun getTaskDistribution(groupId: Long) = mapOf(
        "type" to "TASK_DISTRIBUTION",
        "value" to 5,
        "details" to mapOf(
            "Carlos Mendoza" to 3,
            "Lucía Torres" to 2,
            "Renzo Díaz" to 1
        ),
        "context" to "groupId=$groupId",
        "summary" to "Distribución de tareas: 5.00"
    )

    fun getRescheduledTasks(groupId: Long) = mapOf(
        "type" to "RESCHEDULED_TASKS",
        "value" to 1,
        "details" to mapOf(
            "total" to 5,
            "rescheduled" to 1
        ),
        "context" to "groupId=$groupId",
        "summary" to "Tareas reprogramadas vs no reprogramadas: 1.00"
    )

    fun getAvgSolutionTime(leaderId: Long) = mapOf(
        "type" to "AVG_SOLUTION_TIME",
        "value" to 3.4,
        "details" to mapOf(
            "completedTasks" to 5
        ),
        "context" to "leaderId=$leaderId",
        "summary" to "Tiempo promedio de solución (líder): 3.4 días"
    )

    fun getAvgDevTime(memberId: Long) = mapOf(
        "type" to "AVG_DEV_TIME",
        "value" to 2.6,
        "details" to mapOf(
            "taskCount" to 5
        ),
        "context" to "memberId=$memberId",
        "summary" to "Tiempo promedio de desarrollo: 2.6 días"
    )
}