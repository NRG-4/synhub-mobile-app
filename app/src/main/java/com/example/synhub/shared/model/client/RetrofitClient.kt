package com.example.synhub.shared.model.client

import com.example.synhub.groups.model.response.GroupWebService
import com.example.synhub.groups.model.response.MembersWebService
import com.example.synhub.invitations.model.response.InvitationsWebService
import com.example.synhub.shared.model.response.LogInWebService
import com.example.synhub.shared.model.response.HomeWebService
import com.example.synhub.shared.model.response.RegisterWebService
import com.example.synhub.tasks.model.response.TasksWebService
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

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8080/api/v1/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val logInWebService: LogInWebService = retrofit.create(LogInWebService::class.java)
    val groupWebService: GroupWebService = retrofit.create(GroupWebService::class.java)
    val homeWebService: HomeWebService = retrofit.create(HomeWebService::class.java)
    val tasksWebService: TasksWebService = retrofit.create(TasksWebService::class.java)
    val membersWebService: MembersWebService = retrofit.create(MembersWebService::class.java)
    val invitationsWebService: InvitationsWebService = retrofit.create(InvitationsWebService::class.java)
    val registerWebService: RegisterWebService = retrofit.create(RegisterWebService::class.java)
}