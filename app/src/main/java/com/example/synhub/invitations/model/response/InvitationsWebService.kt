package com.example.synhub.invitations.model.response

import com.example.synhub.invitations.application.dto.InvitationResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path
import retrofit2.http.Query

interface InvitationsWebService {
    @GET("invitations/group")
    suspend fun getGroupInvitations(): List<InvitationResponse>

    @PATCH("group/invitations/{invitationId}")
    suspend fun processInvitation(
        @Path("invitationId") invitationId: Long,
        @Query("accept") accept: Boolean
    ): Response<Void>
}