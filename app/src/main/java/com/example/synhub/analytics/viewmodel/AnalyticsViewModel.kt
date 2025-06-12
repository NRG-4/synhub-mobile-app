package com.example.synhub.analytics.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.synhub.analytics.application.dto.AnalyticsResponse
import com.example.synhub.analytics.application.dto.GroupMemberCountResponse
import com.example.synhub.analytics.application.dto.TaskTimePassedResponse
import com.example.synhub.analytics.model.response.AnalyticsWebService
import com.example.synhub.groups.application.dto.GroupResponse
import com.example.synhub.groups.application.dto.MemberResponse
import com.example.synhub.groups.model.response.GroupWebService
import com.example.synhub.groups.model.response.MembersWebService
import com.example.synhub.shared.model.client.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class AnalyticsState(
    val taskOverview: AnalyticsResponse? = null,
    val taskDistribution: AnalyticsResponse? = null,
    val rescheduledTasks: AnalyticsResponse? = null,
    val avgDevTime: AnalyticsResponse? = null,
    val groupMemberCount: GroupMemberCountResponse? = null,
    val taskTimePassed: TaskTimePassedResponse? = null
)

class AnalyticsViewModel : ViewModel() {
    private val groupApi = RetrofitClient.groupWebService as GroupWebService
    private val memberApi = RetrofitClient.membersWebService as MembersWebService
    private val analyticsApi = RetrofitClient.analyticsWebService as AnalyticsWebService

    private val _haveGroup = MutableStateFlow(false)
    val haveGroup: StateFlow<Boolean> = _haveGroup.asStateFlow()

    private val _group = MutableStateFlow<GroupResponse?>(null)
    val group: StateFlow<GroupResponse?> = _group.asStateFlow()

    private val _members = MutableStateFlow<List<MemberResponse>>(emptyList())
    val members: StateFlow<List<MemberResponse>> = _members.asStateFlow()

    private val _haveMembers = MutableStateFlow(false)
    val haveMembers: StateFlow<Boolean> = _haveMembers.asStateFlow()

    private val _analyticsState = MutableStateFlow(AnalyticsState())
    val analyticsState: StateFlow<AnalyticsState> = _analyticsState.asStateFlow()

    private val _loading = MutableStateFlow(true)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    fun fetchAnalyticsData(token: String) {
        viewModelScope.launch {
            _loading.value = true
            val groupResponse = groupApi.getLeaderGroups()
            val group = groupResponse.body()
            if (group != null) {
                _group.value = group
                _haveGroup.value = true
                val membersResponse = memberApi.getGroupMembers()
                val members = membersResponse.body() ?: emptyList()
                _members.value = members
                _haveMembers.value = members.isNotEmpty()

                val groupId = group.id
                val memberId = members.firstOrNull()?.id
                val taskId = memberId?.let {
                    val tasksResponse = memberApi.getMemberTasks(it)
                    tasksResponse.body()?.firstOrNull()?.id
                }

                val taskOverview = groupId?.let { analyticsApi.getTaskOverview(it).body() }
                val taskDistribution = groupId?.let { analyticsApi.getTaskDistribution(it).body() }
                val rescheduledTasks = groupId?.let { analyticsApi.getRescheduledTasks(it).body() }
                val avgDevTime = memberId?.let { analyticsApi.getAvgDevTime(it).body() }
                val groupMemberCount = groupId?.let { analyticsApi.getGroupMemberCount(it).body() }
                val taskTimePassed = taskId?.let { analyticsApi.getTaskTimePassed(it).body() }

                _analyticsState.value = AnalyticsState(
                    taskOverview = taskOverview,
                    taskDistribution = taskDistribution,
                    rescheduledTasks = rescheduledTasks,
                    avgDevTime = avgDevTime,
                    groupMemberCount = groupMemberCount,
                    taskTimePassed = taskTimePassed
                )
            } else {
                _group.value = null
                _haveGroup.value = false
                _members.value = emptyList()
                _haveMembers.value = false
                _analyticsState.value = AnalyticsState()
            }
            _loading.value = false
        }
    }
}
