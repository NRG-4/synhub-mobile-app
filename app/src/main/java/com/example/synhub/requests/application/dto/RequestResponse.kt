package com.example.synhub.requests.application.dto

data class RequestResponse(
    val id: Long,
    val description: String,
    val requestType: String,
    val requestStatus: String,
    val taskId: Long,
    val memberId: Long,
    val createdAt: String,
    val updatedAt: String
)
