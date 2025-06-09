package com.example.synhub.tasks.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.synhub.tasks.application.dto.TaskRequest
import com.example.synhub.tasks.application.dto.TaskResponse
import com.example.synhub.shared.model.client.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TaskViewModel : ViewModel() {
    private val _tasks = MutableStateFlow<List<TaskResponse>>(emptyList())
    val tasks: StateFlow<List<TaskResponse>> = _tasks

    private val _task = MutableStateFlow<TaskResponse?>(null)
    val task: StateFlow<TaskResponse?> = _task

    //TO-DO: Borrar los logs de depuración antes de la entrega final
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

    //TO-DO: Borrar los logs de depuración antes de la entrega final
    fun fetchTaskById(taskId: Long) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.tasksWebService.getTaskById(taskId)
                android.util.Log.d("TaskViewModel", "Respuesta tarea por ID: ${response.code()} - body: ${response.body()}")
                if (response.isSuccessful && response.body() != null) {
                    val task = response.body()!!
                    _task.value = task
                    android.util.Log.d("TaskViewModel", "Tarea obtenida: ${task.title}")
                } else {
                    _task.value = null
                    android.util.Log.d("TaskViewModel", "No se obtuvo tarea o respuesta inesperada: ${response.code()}")
                }
            } catch (e: Exception) {
                _task.value = null
                android.util.Log.e("TaskViewModel", "Error al obtener tarea por ID", e)
            }
        }
    }

    fun createTask(
        memberId: Long,
        taskRequest: TaskRequest
    ) {
        viewModelScope.launch {
            try {
                android.util.Log.d("TaskViewModel", "Iniciando creación de tarea para miembro: $memberId")
                val response = RetrofitClient.tasksWebService.createTask(memberId, taskRequest)
                android.util.Log.d("TaskViewModel", "Respuesta creación tarea: ${response.code()} - body: ${response.body()}")
                if (response.isSuccessful && response.body() != null) {
                    _task.value = response.body()
                    android.util.Log.d("TaskViewModel", "Tarea creada exitosamente: ${response.body()!!.title}")
                } else {
                    android.util.Log.e("TaskViewModel", "Fallo al crear tarea: ${response.code()} - ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                android.util.Log.e("TaskViewModel", "Error al crear tarea", e)
            }
        }
    }
}