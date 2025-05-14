package com.example.synhub.requests.model.response

import com.example.synhub.requests.model.Requests
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface RequestsWebService {
    // GET a specific request
    @GET("/api/v1/{memberId}/tasks/{taskId}/requests/{requestId}")
    suspend fun getRequestById(
        @Path("memberId") memberId: Long,
        @Path("taskId") taskId: Long,
        @Path("requestId") requestId: Long
    ): Requests

    // POST a new request
    @POST("/api/v1/{memberId}/tasks/{taskId}/requests")
    suspend fun createRequest(
        @Path("memberId") memberId: Long,
        @Path("taskId") taskId: Long,
        @Body request: Requests
    ): Requests

    // PUT to update request status
    @PUT("/api/v1/{memberId}/tasks/{taskId}/requests/{requestId}/status")
    suspend fun updateRequestStatus(
        @Path("memberId") memberId: Long,
        @Path("taskId") taskId: Long,
        @Path("requestId") requestId: Long,
        @Body status: String
    ): Requests
}