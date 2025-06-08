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

    fun fetchRequest(taskId: Long) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.requestsWebService.getRequest(taskId);
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
}