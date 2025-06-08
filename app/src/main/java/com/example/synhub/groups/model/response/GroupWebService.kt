package com.example.synhub.groups.model.response

import com.example.synhub.groups.application.dto.GroupMember
import com.example.synhub.groups.application.dto.GroupRequest
import com.example.synhub.groups.application.dto.GroupResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface GroupWebService {
    @GET("leader/group")
    suspend fun getLeaderGroups(): Response<GroupResponse>

    @GET("groups/members")
    suspend fun getGroupMembers(): Response<List<GroupMember>>

    @POST("leader/group")
    suspend fun createGroup(@Body groupRequest: GroupRequest): Response<GroupResponse>

    @DELETE("leader/group/members/{memberId}")
    suspend fun deleteGroupMember(@Path("memberId") memberId: Long): Response<Void>
}
