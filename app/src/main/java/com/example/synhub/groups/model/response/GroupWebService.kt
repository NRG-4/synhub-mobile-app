package com.example.synhub.groups.model.response

import com.example.synhub.groups.application.dto.GroupMember
import com.example.synhub.groups.application.dto.GroupResponse
import retrofit2.Response
import retrofit2.http.GET

interface GroupWebService {
    @GET("leader/group")
    suspend fun getLeaderGroups(): Response<GroupResponse>

    @GET("groups/members")
    suspend fun getGroupMembers(): Response<List<GroupMember>>
}
