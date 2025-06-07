package com.example.synhub.tasks.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.synhub.tasks.application.dto.TaskResponse
import com.example.synhub.shared.model.client.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TaskViewModel : ViewModel() {
    private val _tasks = MutableStateFlow<List<TaskResponse>>(emptyList())
    val tasks: StateFlow<List<TaskResponse>> = _tasks

    fun fetchGroupTasks() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.tasksWebService.getGroupTasks()
                android.util.Log.d("TaskViewModel", "Respuesta tareas: ${response.code()} - body: ${response.body()}")
                if (response.isSuccessful && response.body() != null) {
                    _tasks.value = response.body()!!
                    android.util.Log.d("TaskViewModel", "Tareas obtenidas: ${_tasks.value.size}")
                } else {
                    _tasks.value = emptyList()
                    android.util.Log.d("TaskViewModel", "No se obtuvieron tareas o respuesta inesperada: ${response.code()}")
                }
            } catch (e: Exception) {
                _tasks.value = emptyList()
                android.util.Log.e("TaskViewModel", "Error al obtener tareas", e)
            }
        }
    }
}