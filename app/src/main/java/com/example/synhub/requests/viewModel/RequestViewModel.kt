package com.example.synhub.requests.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.synhub.requests.application.dto.CreateRequest
import com.example.synhub.requests.application.dto.RequestResponse
import com.example.synhub.shared.model.client.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RequestViewModel: ViewModel() {
    private val _request = MutableStateFlow<RequestResponse?>(null)
    val request: StateFlow<RequestResponse?> = _request

    private val _requests = MutableStateFlow<List<RequestResponse>>(emptyList())
    val requests: StateFlow<List<RequestResponse>> = _requests

    fun createRequest(taskId: Long, createRequest: CreateRequest) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.requestsWebService.createRequest(taskId, createRequest)
                if (response.isSuccessful && response.body() != null) {
                    _request.value = response.body()
                } else {
                    _request.value = null
                }
            } catch (e: Exception) {
                _request.value = null
            }
        }
    }

    fun fetchRequestByTaskId(taskId: Long) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.requestsWebService.getRequestByTaskId(taskId);
                if (response.isSuccessful && response.body() != null) {
                    val request = response.body()!!
                    _request.value = request
                } else {
                    _request.value = null
                }
            } catch (e: Exception) {
                _request.value = null
            }
        }
    }

    fun updateRequestStatus(taskId: Long, status: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.requestsWebService.updateRequestStatus(taskId, status)
                if (response.isSuccessful && response.body() != null) {
                    _request.value = response.body()
                } else {
                    _request.value = null
                }
            } catch (e: Exception) {
                _request.value = null
            }
        }
    }

    // Not tested yet
    fun deleteRequest(taskId: Long) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.requestsWebService.deleteRequest(taskId)
                if (response.isSuccessful) {
                    _request.value = null
                } else {
                    _request.value = null
                }
            } catch (e: Exception) {
                _request.value = null
            }
        }
    }

    fun fetchGroupRequests() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.requestsWebService.getGroupRequests()
                if (response.isSuccessful && response.body() != null) {
                    _requests.value = response.body()!!
                } else {
                    _requests.value = emptyList()
                }
            } catch (e: Exception) {
                _requests.value = emptyList()
            }
        }
    }

    fun fetchMemberRequests() {
        viewModelScope.launch {
            try {



                val response = RetrofitClient.requestsWebService.getMemberRequests()
                if (response.isSuccessful && response.body() != null) {
                    _requests.value = response.body()!!
                } else {
                    _requests.value = emptyList()
                }
            } catch (e: Exception) {
                _requests.value = emptyList()
            }
        }
    }

    // Fuse group and member requests in a single function maybe? (has to check if its leader or not)
}