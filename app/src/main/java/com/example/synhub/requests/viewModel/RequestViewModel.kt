package com.example.synhub.requests.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.synhub.requests.model.Requests
import com.example.synhub.shared.model.client.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RequestViewModel: ViewModel() {
    var request: Requests? by mutableStateOf(null)
    val requestId = 1

    fun getRequestById() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.logInWebService.getRequestById(1)
            withContext(Dispatchers.Main) {
                request = if (response.isSuccessful) {
                    response.body()
                } else {
                    null
                }
            }
        }
    }
}