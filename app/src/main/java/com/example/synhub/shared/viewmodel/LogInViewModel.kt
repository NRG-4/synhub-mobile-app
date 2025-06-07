package com.example.synhub.shared.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.synhub.shared.application.dto.SignInRequest
import com.example.synhub.shared.application.dto.SignInResponse
import com.example.synhub.shared.model.client.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LogInViewModel : ViewModel() {
    var signInResponse: SignInResponse? = null
        private set

    private val _loginSuccess = MutableStateFlow<Boolean?>(null)
    val loginSuccess: StateFlow<Boolean?> = _loginSuccess

    fun signIn(username: String, password: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.logInWebService.signIn(SignInRequest(username, password))
                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let {
                        signInResponse = it
                        RetrofitClient.updateToken(it.token)
                        _loginSuccess.value = true
                        return@launch
                    }
                }
                _loginSuccess.value = false
            } catch (e: Exception) {
                Log.e("LoginPrueba", "Exception: ${e.message}")
                _loginSuccess.value = false
            }
        }
    }

    suspend fun getLeaderDetails(): Boolean {
        return try {
            val response = RetrofitClient.homeWebService.getLeaderDetails()
            response.isSuccessful && response.body() != null
        } catch (e: Exception) {
            false
        }
    }

    // Nueva función para resetear el estado de loginSuccess
    fun resetLoginState() {
        _loginSuccess.value = null
    }
}