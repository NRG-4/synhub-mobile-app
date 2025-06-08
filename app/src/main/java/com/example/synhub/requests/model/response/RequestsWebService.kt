package com.example.synhub.requests.model.response

import com.example.synhub.requests.application.dto.CreateRequest
import com.example.synhub.requests.application.dto.RequestResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface RequestsWebService {
    @GET("tasks/{taskId}/request")
    suspend fun getRequest(
        @Path("taskId") taskId: Long
    ): Response<RequestResponse>

    @POST("tasks/{taskId}/request")
    suspend fun createRequest(
        @Path("taskId") taskId: Long,
        @Body request: CreateRequest
    ): Response<RequestResponse>

    @PUT("tasks/{taskId}/request/status/{status}")
    suspend fun updateRequestStatus(
        @Path("taskId") taskId: Long,
        @Path("status") status: String
    ): Response<RequestResponse>
}