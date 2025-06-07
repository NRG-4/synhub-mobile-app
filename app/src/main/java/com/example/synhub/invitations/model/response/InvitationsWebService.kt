package com.example.synhub.invitations.model.response

import com.example.synhub.invitations.application.dto.InvitationResponse
import retrofit2.http.GET

interface InvitationsWebService {
    @GET("invitations/group")
    suspend fun getGroupInvitations(): List<InvitationResponse>
}