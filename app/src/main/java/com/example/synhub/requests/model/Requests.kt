package com.example.synhub.requests.model

data class Requests(
    var id: Long,
    var description: String,
    var requestType: String,
    var requestStatus: String,
    var taskId: Long,
    var memberId: Long
)
