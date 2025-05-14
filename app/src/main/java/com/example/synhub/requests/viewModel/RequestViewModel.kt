package com.example.synhub.requests.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.synhub.requests.model.Requests
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RequestViewModel: ViewModel() {
    var requestsList: ArrayList<Requests> by mutableStateOf(arrayListOf())

    fun getRequestById() {
        viewModelScope.launch(Dispatchers.IO) {

        }
    }
}