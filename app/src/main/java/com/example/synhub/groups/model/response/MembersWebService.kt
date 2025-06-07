package com.example.synhub.groups.model.response

import com.example.synhub.groups.application.dto.MemberResponse
import com.example.synhub.groups.application.dto.NextTaskResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MembersWebService {
    @GET("groups/members")
    suspend fun getGroupMembers(): Response<List<MemberResponse>>

    @GET("members/{memberId}/tasks/next")
    suspend fun getNextTaskForMember(@Path("memberId") memberId: Long): Response<NextTaskResponse>}