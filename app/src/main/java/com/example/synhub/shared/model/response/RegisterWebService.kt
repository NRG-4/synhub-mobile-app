package com.example.synhub.shared.model.response

import com.example.synhub.shared.application.dto.SignUpRequest
import com.example.synhub.shared.application.dto.SignUpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterWebService {
    @POST("authentication/sign-up")
    suspend fun signUp(@Body request: SignUpRequest): Response<SignUpResponse>
}