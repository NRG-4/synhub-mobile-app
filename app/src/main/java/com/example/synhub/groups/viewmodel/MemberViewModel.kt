package com.example.synhub.groups.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.synhub.groups.application.dto.MemberResponse
import com.example.synhub.groups.application.dto.NextTaskResponse
import com.example.synhub.groups.model.response.MembersWebService
import com.example.synhub.shared.model.client.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MemberViewModel : ViewModel() {
    private val _members = MutableStateFlow<List<MemberResponse>>(emptyList())
    val members: StateFlow<List<MemberResponse>> = _members

    private val _haveMembers = MutableStateFlow(false)
    val haveMembers: StateFlow<Boolean> = _haveMembers

    private val _nextTask = MutableStateFlow<NextTaskResponse?>(null)
    val nextTask: StateFlow<NextTaskResponse?> = _nextTask

    private val _nextTaskMap = MutableStateFlow<Map<Long, NextTaskResponse?>>(emptyMap())
    val nextTaskMap: StateFlow<Map<Long, NextTaskResponse?>> = _nextTaskMap

    //TO-DO: Borrar los logs de producción
    fun fetchGroupMembers() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.membersWebService.getGroupMembers()
                android.util.Log.d("MemberViewModel", "Respuesta miembros: ${response.code()} - body: ${response.body()}")
                if (response.isSuccessful && response.body() != null) {
                    _members.value = response.body()!!
                    _haveMembers.value = _members.value.isNotEmpty()
                    android.util.Log.d("MemberViewModel", "Miembros obtenidos: ${_members.value.size}")
                } else {
                    _members.value = emptyList()
                    _haveMembers.value = false
                    android.util.Log.d("MemberViewModel", "No se obtuvieron miembros o respuesta inesperada: ${response.code()}")
                }
            } catch (e: Exception) {
                _members.value = emptyList()
                _haveMembers.value = false
                android.util.Log.e("MemberViewModel", "Error al obtener miembros", e)
            }
        }
    }

    //TO-DO: Borrar los logs de producción
    fun fetchNextTask(memberId: Long) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.membersWebService.getNextTaskForMember(memberId)
                android.util.Log.d("MemberViewModel", "Respuesta nextTask: ${response.code()} - body: ${response.body()}")
                if (response.isSuccessful && response.body() != null) {
                    _nextTaskMap.value = _nextTaskMap.value.toMutableMap().apply { put(memberId, response.body()!!) }
                    android.util.Log.d("MemberViewModel", "NextTask obtenida: ${response.body()?.title}")
                } else {
                    _nextTaskMap.value = _nextTaskMap.value.toMutableMap().apply { put(memberId, null) }
                    android.util.Log.d("MemberViewModel", "No se obtuvo nextTask o respuesta inesperada: ${response.code()}")
                }
            } catch (e: Exception) {
                _nextTaskMap.value = _nextTaskMap.value.toMutableMap().apply { put(memberId, null) }
                android.util.Log.e("MemberViewModel", "Error al obtener nextTask", e)
            }
        }
    }
}