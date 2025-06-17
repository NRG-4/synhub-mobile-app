package com.example.synhub.requests.views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.synhub.R
import com.example.synhub.requests.application.dto.RequestResponse
import com.example.synhub.requests.viewModel.RequestViewModel
import com.example.synhub.shared.components.TopBar
import com.example.synhub.tasks.application.dto.TaskResponse
import com.example.synhub.tasks.viewmodel.TaskViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.time.temporal.ChronoField

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ValidationView(nav: NavHostController, taskId: String?) {
    val requestViewModel: RequestViewModel = viewModel()
    val request by requestViewModel.request.collectAsState()

    val taskViewModel: TaskViewModel = viewModel()
    val task by taskViewModel.task.collectAsState()

    LaunchedEffect(taskId) {
        taskId?.toLongOrNull()?.let {
            requestViewModel.fetchRequestByTaskId(it)
        }
    }

    LaunchedEffect(request?.taskId) {
        request?.taskId?.let {
            taskViewModel.fetchTaskById(it)
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFFFFFFFF),
        topBar = {
            TopBar(
                function = {
                    nav.popBackStack()
                },
                title = "Validación de Tarea",
                Icons.AutoMirrored.Filled.ArrowBack
            )
        }
    ) {
            innerPadding -> ValidationDetails(modifier = Modifier.padding(innerPadding),nav, request, task)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ValidationDetails(
    modifier: Modifier,
    nav: NavHostController,
    request: RequestResponse?,
    task: TaskResponse?,
    requestViewModel: RequestViewModel = RequestViewModel(),
    taskViewModel: TaskViewModel = TaskViewModel()) {

    val statusColor = when (task?.status) {
        "COMPLETED" -> Color(0xFF4CAF50) // Green
        "EXPIRED" -> Color(0xFFFF5252)   // Red
        "IN_PROGRESS" -> Color(0xFFFFC107)   // Amber
        else -> Color(0xFFE0E0E0)        // Default gray
    }

    fun formatDate(timestamp: String?): String {
        return try {
            val inputFormatter = DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd HH:mm:ss")
                .appendFraction(ChronoField.MICRO_OF_SECOND, 0, 6, true)
                .toFormatter()
            val outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            timestamp?.let {
                LocalDateTime.parse(it, inputFormatter).format(outputFormatter)
            } ?: ""
        } catch (e: Exception) {
            ""
        }
    }

    Column(
        modifier = Modifier
            .padding(30.dp)
            .padding(top = 90.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.becker), // Replace with actual image
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = task?.member?.name + " " + task?.member?.surname,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp)
        }
        Card(
            modifier = Modifier
                .padding(vertical = 15.dp),
            elevation = CardDefaults
                .cardElevation(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    modifier = Modifier
                        .padding(10.dp)
                        .height(160.dp)
                        .fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF1A4E85)
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                    ) {
                        Text(text = task?.title.toString(), color = Color.White)
                        Spacer(modifier = Modifier.height(10.dp))
                        HorizontalDivider(thickness = 2.dp)
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = task?.description.toString(), color = Color.White)
                    }
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(8.dp)
                ) {
                    Text(
                        text = when (task?.status) {
                            "COMPLETED" -> "Tiempo de desarrollo"
                            else -> "Tiempo de desarrollo asignado"
                        },
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = when (task?.status) {
                            "COMPLETED" -> formatDate(task.createdAt)
                            else -> formatDate(task?.createdAt) + " - " + formatDate(task?.dueDate)
                        },
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Box(
                        modifier = Modifier
                            .height(10.dp)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(4.dp))
                            .background(statusColor)
                    )
                }

            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // TODO: When pressed, edit due date of task and set task to "PENDING"
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFF832A)
            ),
            elevation = ButtonDefaults
                .buttonElevation(5.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
        ) {
            Icon(
                Icons.Default.DateRange,
                contentDescription = null)
            Spacer(
                modifier = Modifier
                .width(8.dp))
            Text("Reprogramar")
        }
        if (task?.status != "EXPIRED" && request?.requestStatus == "COMPLETED") {
            Button(
                onClick = {
                    task?.id?.let { taskId ->
                        requestViewModel.updateRequestStatus(taskId, "APPROVED")
                        // taskViewModel.updateTaskStatus(taskId, "DONE")
                        nav.popBackStack()
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4CAF50)
                ),
                elevation = ButtonDefaults
                    .buttonElevation(5.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Icon(
                    Icons.Default.CheckCircle,
                    contentDescription = null)
                Spacer(
                    modifier = Modifier
                        .width(8.dp))
                Text("Marcar como completado")
            }
        }

    }

}