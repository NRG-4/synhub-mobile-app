package com.example.synhub.shared.model.response

import com.example.synhub.requests.model.Requests
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface WebService {
    // Request and validations
    @GET("requests/{requestId}")
    suspend fun getRequestById(@Path("requestId") requestId:Long): Response<Requests>
}