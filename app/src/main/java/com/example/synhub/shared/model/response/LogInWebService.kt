package com.example.synhub.shared.model.response

import com.example.synhub.requests.application.dto.RequestResponse
import com.example.synhub.shared.application.dto.SignInRequest
import com.example.synhub.shared.application.dto.SignInResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface LogInWebService {
    // Request and validations
    @GET("requests/{requestId}")
    suspend fun getRequestById(@Path("requestId") requestId:Long): Response<RequestResponse>

    @POST("authentication/sign-in")
    suspend fun signIn(@Body request: SignInRequest): Response<SignInResponse>
}