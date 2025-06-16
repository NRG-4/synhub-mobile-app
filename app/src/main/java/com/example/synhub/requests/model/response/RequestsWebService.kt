package com.example.synhub.requests.model.response

import com.example.synhub.requests.application.dto.CreateRequest
import com.example.synhub.requests.application.dto.RequestResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface RequestsWebService {
    // Create request
    @POST("tasks/{taskId}/request")
    suspend fun createRequest(
        @Path("taskId") taskId: Long,
        @Body request: CreateRequest
    ): Response<RequestResponse>

    // Get request by task ID
    @GET("tasks/{taskId}/request")
    suspend fun getRequestByTaskId(
        @Path("taskId") taskId: Long
    ): Response<RequestResponse>

    // Update request status
    @PUT("tasks/{taskId}/request/status/{status}")
    suspend fun updateRequestStatus(
        @Path("taskId") taskId: Long,
        @Path("status") status: String
    ): Response<RequestResponse>

    // Delete request by task ID
    @DELETE("tasks/{taskId}/request")
    suspend fun deleteRequest(
        @Path("taskId") taskId: Long
    ): Response<Unit>

    // Get all requests from a group
    @GET("leader/group/requests")
    suspend fun getGroupRequests(): Response<List<RequestResponse>>

    // Get all requests from a member
    @GET("leader/member/requests")
    suspend fun getMemberRequests(): Response<List<RequestResponse>>
}