package com.example.synhub.shared.model.client

import com.example.synhub.shared.model.response.SharedWebService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    @Volatile
    private var token: String? = null

    fun updateToken(newToken: String) {
        token = newToken
    }

    private val client: OkHttpClient
        get() = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val requestBuilder = chain.request().newBuilder()
                token?.let {
                    requestBuilder.addHeader("Authorization", "Bearer $it")
                }
                chain.proceed(requestBuilder.build())
            }
            .build()

    val webService: SharedWebService = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8080/api/v1/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(SharedWebService::class.java)
}