package com.example.synhub.groups.application.dto

data class NextTaskResponse(
    val id: Long,
    val title: String,
    val description: String,
    val dueDate: String,
    val createdAt: String
)

