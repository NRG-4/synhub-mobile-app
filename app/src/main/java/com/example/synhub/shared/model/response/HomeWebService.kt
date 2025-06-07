package com.example.synhub.shared.model.response

import com.example.synhub.shared.application.dto.Leader
import retrofit2.Response
import retrofit2.http.GET

interface HomeWebService {
    @GET("leader/details")
    suspend fun getLeaderDetails(): Response<Leader>
}

