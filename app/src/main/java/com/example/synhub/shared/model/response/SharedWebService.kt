package com.example.synhub.shared.model.response

import com.example.synhub.requests.model.Requests
import com.example.synhub.shared.application.dto.Leader
import com.example.synhub.shared.application.dto.SignInRequest
import com.example.synhub.shared.application.dto.SignInResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SharedWebService {
    // Request and validations
    @GET("requests/{requestId}")
    suspend fun getRequestById(@Path("requestId") requestId:Long): Response<Requests>

    @POST("authentication/sign-in")
    suspend fun signIn(@Body request: SignInRequest): Response<SignInResponse>

    @GET("leader/details")
    suspend fun getLeaderDetails(): Response<Leader>
}